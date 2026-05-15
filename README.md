# Rule-Based Language with Execution and Static Analysis

## Authors
- Cristian David Bolaños Giraldo
- Juan Pablo Lopera

## Project Description
This project implements a rule-based language that supports parsing, abstract syntax tree construction, execution, and basic static analysis. The system processes rules of the form `if <condition> then <action>`, evaluates them over an initial environment, derives active facts using a fixed-point strategy, and reports static analysis results such as conflicts, redundancies, and potentially inactive rules.

The implementation follows the core requirements of the assignment:
- Lexical analysis
- Syntactic analysis
- AST construction
- Interpreter execution
- Basic static analysis

## Supported Language Features
The language supports:
- Rules with the form `rule <id>: if <condition> then <action>`
- Atomic conditions based on comparisons:
  - `id > value`
  - `id < value`
  - `id = value`
- Fact conditions using identifiers
- Conjunctions with `AND`
- Actions that activate facts

## Canonical AST Structure
The implementation represents programs using an Abstract Syntax Tree with the following logical structure:
- **Program**: list of rules
- **Rule**: name, condition, action
- **Condition**:
  - atomic condition, or
  - conjunction of two conditions
- **Atomic condition**:
  - comparison, or
  - fact
- **Action**: identifier to activate

## Project Structure
A possible modular structure for the project is:

```
rule-based-language
├─ README.md
└─ src
   ├─ main
   │  ├─ analysis
   │  │  └─ StaticAnalyzer.java
   │  ├─ ast
   │  │  ├─ ActionNode.java
   │  │  ├─ AtomNode.java
   │  │  ├─ ConditionNode.java
   │  │  ├─ ProgramNode.java
   │  │  └─ RuleNode.java
   │  ├─ interpreter
   │  │  ├─ Environment.java
   │  │  └─ Interpreter.java
   │  ├─ lexer
   │  │  ├─ Lexer.java
   │  │  ├─ Token.java
   │  │  └─ TokenType.java
   │  ├─ Main.java
   │  └─ parser
   │     └─ Parser.java
   └─ test
      ├─ caso1_rules.txt
      └─ caso1_state.txt

```

## Execution Model
The interpreter evaluates the program over an environment containing:
- Variables with integer values
- Active facts

Execution is performed using a fixed-point strategy:
1. Initialize the environment with the given initial state.
2. Evaluate all rules.
3. Collect actions of applicable rules.
4. Activate the resulting facts.
5. Repeat until no new facts are produced.

The final output is the set of activated facts, printed once each and sorted lexicographically.

## Static Analysis
The project includes a basic static analysis module that reports:
- **Conflicts**: when multiple rules generate the same action
- **Redundancies**: when different rules have the same condition and action
- **Potentially inactive rules**: rules that are detected as inactive according to the implemented analysis criterion

## Input Format
The program is divided into two parts:
1. A set of rules
2. An optional initial state

### Rules
Each rule follows this format:

```text
rule r1:
if temp > 30 then alert
```

### Initial State
The initial state may contain:
- Variable assignments, for example:

```text
temp = 35
humidity = 40
```

- Active facts, for example:

```text
alert
```

Each element must appear on a separate line.

## How to Compile
Example using `javac`:

```powershell
javac -d out (Get-ChildItem -Recurse -Filter "*.java" src\main | ForEach-Object { $_.FullName })
```

## How to Run
Example using `java`:

```powershell
java -cp out main.Main
```

If the implementation expects rule and state files, run it according to the paths configured in `Main.java` or provide the corresponding command-line arguments if supported.

## Output Format
The program prints:
1. The execution output, one activated fact per line, in lexicographical order
2. Then the static analysis messages, one per line

If no facts are activated, the output must be exactly:

```text
(no output)
```

## Design Decisions
- The implementation is organized modularly to separate lexer, parser, AST, interpreter, and analyzer responsibilities.
- The interpreter and static analyzer operate over the same rule representation built from the AST.
- Static analysis is reported after program execution, following the assignment format.
- The implementation aims to remain close to the restricted language defined in the assignment, without introducing extra features such as `OR`, `NOT`, or parentheses.

## Tools and Environment
Complete this section with the versions actually used in the project:
- Operating system: `<add OS version>`
- Programming language: Java
- Java version: `<add Java version>`
- Additional tools: `<add tools if needed>`

## Notes
- Keywords are case-sensitive.
- Identifiers should follow the constraints defined in the assignment.
- Extra whitespace and blank lines should be ignored by the implementation.

## Submission Notes
This project was developed for the Formal Languages assignment on rule-based language implementation with execution and static analysis.

