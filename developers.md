# Developer's Memo

## For All Developers

### Development Tools

* Android Studio Arctic Fox | 2020.3.1

### How to Build

We use [FRExtensions](https://sallyluenoa.github.io/FRExtensions/) in this project so that you should create a GitHub personal access token, as the same way to "How to Install".  
Then open the project in Android Studio and modify your user name and access token in build.gradle of the root, or register "GITHUB_ACTOR" and "GITHUB_TOKEN" in the system environment.

### Branch Management

The master branch is the latest one that has been public released.  
The develop branch is the latest development one that has not been released yet.

## For Administrators

### How to Release

We deploy releases via GitHub Actions.  
It would be triggered when pushed on the master branch.

1. Check out the develop branch.
2. Open `_version.yml` and update the version of `global`.
3. Add a commit for updated version.
4. Create a pull request from the develop to the master.
5. When merged via the pull request, the release deploy will be run automatically.
