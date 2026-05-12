javac -d out/ (Get-ChildItem -Recurse -Filter "*.java" src\main\java | % { $_.FullName })

java -cp out main.Main