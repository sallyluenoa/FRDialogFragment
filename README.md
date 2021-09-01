# FRFragmentListener

FRFragmentListener is a library of Kotlin Android that efficiently notifies the parent activity of events from a fragment.  
It also provides a general dialog fragment that can be show maximum three buttons.

## Background

We need to consider the re-creation of the activity like screen rotation when notifying the parent activity of events from the fragment in Android.  
The general solution is to have the parent activity implement a callback listener for the fragment and define override methods of the listener in the activity.  
However, there are cases that the activity defines many fragments or the fragment listener defines many events.  
The more override methods you define for an activity, the more redundant the code becomes.

In particular, the implementation of dialogs in Android is highly recommended to be displayed by DialogFragment added in API11, instead of calling AlertDialog itself.  
The general fragments, including the dialog fragment, will be called in multiple cases.  
The problem is that it is hard to see the code because it have to register the request code in the fragment and check the code every time in the override method of the listener in the activity.

In our library, the fragment listeners are registered with the activity in advance.  
This allows the listeners without definitions of the override methods of them in the parent activity.  
In addition, since each listener is registered individually, it allows the listeners to separate for different purposes thus improving readability.

## How to Install

### Create a Personal Access Token

You should create a GitHub personal access token to read the GitHub packages in your Android project.

1. Open your personal GitHub account.
1. Settings -> Developer Settings -> Personal access tokens -> Generate new token
1. Generate a new token. Scopes would be selected "read:packages" only.
1. You should keep the created token.

### Install your Android Project with Gradle

In your Android project, open build.gradle of the root and add the following settings.  
`ACCOUNT_NAME` is your GitHub account name and `ACCESS_TOKEN` is the token you created above.

```Gradle
allprojects {
    repositories {
        google()
        mavenCentral()

        // Add this settings.
        maven {
            name = 'GitHubPackages'
            url = uri('https://maven.pkg.github.com/sallyluenoa/FRFragmentListener')
            credentials {
                username = ACCOUNT_NAME
                password = ACCESS_TOKEN
            }
        }
    }
}
```

Open build.gradle of the module and add the following dependency.

```Gradle
implementation 'org.fog-rock.frfragmentlistener:frfragmentlistener:1.0.0'
```

Then sync your project with Gradle.  
The library will be installed in your project.

## Releases

Please read [GitHub Releases](https://github.com/sallyluenoa/FRFragmentListener/releases).

## Documents

[A latest version](./docs/index.html) can be read witch written with Kdoc formats.  
Old versions are available from Assets of the past releases.

## License

This license is [Apache License 2.0](./LICENSE.txt).

## For Developers

Please read [developer's memo](./developers.md).