# programming

Create a repository: on github

On the laptop:

- Change directory to Documents:
```bash
cd $documents
```

- Change directory to the right folder:
```bash
cd programming
```

Or make the two commands in one command:
```bash
cd $documents/programming
```

Return to initial path:
```bash
cd
```

List content of a folder:
```bash
ls
```

Clone a repository
```
git clone blablabla
```

Change branch (TODO: one branch per tp !!!)
```bash
git checkout tp1
```

Create branch and go on the new branch:
```bash
git checkout -b big_changes
```

!!!! IMPORTANT !!!!!
See status of the branch:
```bash
git status
```

Add your file for a backup:
```bash
git add *
```

Create a local checkpoint (allow to recover a previous version of the code)
```bash
git commit -m "Your message here !!!"
```

Push your changes on the cloud:
```bash
git push
```

# SCALA

Compile your program
```bash
scalac main.scala
```

Run your program
```bash
scala 
```
