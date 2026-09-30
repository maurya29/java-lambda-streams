"""Convert the supplied workbook into one Java class per topic."""
import html
import re
import sys
from pathlib import Path

root = Path(__file__).resolve().parents[1]
source = Path(sys.argv[1]).read_text(encoding='utf-8-sig')
base = root / 'src/main/java/com/interview/streams'
base.mkdir(parents=True, exist_ok=True)
headers = list(re.finditer(r'^Topic (\d+)\s*\n\s*\n([^\n]+)\n([^\n]+)', source, re.M))
assert len(headers) == 18
imports = 'import java.security.SecureRandom;\nimport java.util.*;\nimport java.util.function.*;\nimport java.util.stream.*;\n'
index = ['# Java Lambda and Streams Interview Practice', '',
         'Java 8 compatible Maven project containing all 18 modules and 149 solutions from the supplied workbook.', '',
         'Each module is one class; numbered comments preserve the question, explanation, trigger point, and complexity. Solution methods are public instance methods. Shared models are in `com.interview.streams.model`.', '',
         '## Build and run', '', 'Requires a JDK and Maven. From this directory:', '',
         '```sh', 'mvn clean package', 'java -jar target/java-lambda-streams-1.0.0.jar', '```', '',
         'Open `pom.xml` in IntelliJ IDEA, Eclipse, or VS Code to import the project.', '',
         '## Modules', '', '| # | Module class | Questions |', '|---|---|---|']
total = 0
for i, header in enumerate(headers):
    number, title, description = header.groups()
    title, description = title.strip(), description.strip()
    name = ''.join(word[0].upper() + word[1:] for word in re.findall(r'[A-Za-z0-9]+', title))
    if name in ('Collectors', 'Optional'):
        name += 'Module'
    block = source[header.end():headers[i+1].start() if i+1 < len(headers) else len(source)]
    problems = list(re.finditer(r'^' + re.escape(title) + r' - Problem (\d+)\s*$', block, re.M))
    declared = int(re.search(r'(\d+) questions', block).group(1))
    assert len(problems) == declared
    bodies = []
    for j, problem in enumerate(problems):
        section = block[problem.end():problems[j+1].start() if j+1 < len(problems) else len(block)]
        note, code = section.split('Java 8 Solution', 1)
        body = code.split('class Solution {', 1)[1].strip()
        assert body.endswith('}')
        body = body[:-1].rstrip()
        # The standalone duplicate-characters answer repeats the preceding public solution.
        body = re.sub(r'\n\s*private Map<Character, Long> characterFrequency\(String text\) \{.*?\n  \}', '', body, flags=re.S)
        lines = body.splitlines()
        if not lines[0].startswith('  '):
            lines[0] = '  ' + lines[0]
        body = '\n'.join(lines)
        body = re.sub(r'^(  )(?!private |public |protected )((?:[\w<>?,\[\] ]+) \w+\([^\n]*\) \{)', r'\1public \2', body, flags=re.M)
        note_lines = [x.strip() for x in note.strip().splitlines() if x.strip()]
        comment = '  /**\n   * Problem ' + problem.group(1) + ': ' + html.escape(note_lines[0]) + '\n'
        comment += ''.join('   * <p>' + html.escape(x) + '</p>\n' for x in note_lines[1:]) + '   */\n'
        bodies.append(comment + body)
    folder = base / 'modules'
    folder.mkdir(exist_ok=True)
    (folder / (name + '.java')).write_text('package com.interview.streams.modules;\n\nimport com.interview.streams.model.*;\n' + imports + '\n/**\n * Module ' + number + ': ' + title + '.\n * ' + description + '\n */\npublic class ' + name + ' {\n\n' + '\n\n'.join(bodies) + '\n}\n', encoding='utf-8')
    index.append(f'| {number} | [{name}](src/main/java/com/interview/streams/modules/{name}.java) | {declared} |')
    total += declared
assert total == 149
models = source[source.index('class Employee {'):headers[0].start()]
for match in re.finditer(r'^class (\w+) \{.*?^\}', models, re.M | re.S):
    name = match.group(1)
    code = match.group().replace('class ' + name, 'public class ' + name, 1)
    code = re.sub(r'^(  )(?!private )(?=\w[^\n]*\()', r'\1public ', code, flags=re.M)
    folder = base / 'model'
    folder.mkdir(exist_ok=True)
    (folder / (name + '.java')).write_text('package com.interview.streams.model;\n\nimport java.util.*;\n\n' + code + '\n', encoding='utf-8')
index += ['', '## Usage', '', '```java', 'LambdaBasics module = new LambdaBasics();', 'List<Integer> sorted = module.sortIntegers(Arrays.asList(3, 1, 2));', '```', '',
          'Import module classes from `com.interview.streams.modules` and models from `com.interview.streams.model`. `CollectorsModule` and `OptionalModule` avoid collisions with JDK types.', '',
          'Solutions retain the workbook conventions: inputs are generally non-null; ranking methods require enough distinct values; missing-number inputs contain distinct values from 1 through n with exactly one missing. Character exercises operate on UTF-16 chars. The parallel timing example is illustrative, not a benchmark. Integer arithmetic has normal Java overflow behavior.', '']
(root / 'README.md').write_text('\n'.join(index), encoding='utf-8')
print(f'Created {len(headers)} module classes with {total} solutions and four model classes.')
