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

In your Android project, open the Gradle file (.gradle or .gradle.kts) of the root and add the following settings.  
`ACCOUNT_NAME` is your GitHub account name and `ACCESS_TOKEN` is the token you created above.

```Gradle
repositories {
    google()
    mavenCentral()

    // Add this settings.
    maven {
        url = uri("https://maven.pkg.github.com/sallyluenoa/FRFragmentListener")
        credentials {
            username = "ACCOUNT_NAME"
            password = "ACCESS_TOKEN"
        }
    }
}
```

Open the Gradle file of the module and add the following dependency, then sync your project with Gradle.  
The library will be installed in your project.

```Gradle
implementation("org.fog-rock.frfragmentlistener:frfragmentlistener:1.0.1")
```

## How to Use

We referred to the Activity Result APIs to implement this library.  
It is a bit similar to the usage of `registerForActivityResult()`, which has replaced `Activity#onActivityResult()`.

### Notify Fragment Events to Parent Activity

Define a sub-interface of `FRFragmentListener` that notifies the parent activity of fragment events.

```kotlin
class SampleFragment : Fragment() {

    interface Listener: FRFragmentListener {

        fun onClickedYesButton()

        fun onClickedNoButton()
    }

}
```

Register the fragment listener and keep the return value as a private field in the subclass of `FRAppCompatActivity`.

```kotlin
class SampleActivity : FRAppCompatActivity() {

    private val fragmentListenerKey = registerForFragmentListener(object : SampleFragment.Listener {
        override fun onClickedYesButton() {
            // Write your result code here!
        }
        override fun onClickedNoButton() {
            // Write your result code here!
        }
    })

}
```

Register the listener key to arguments when generate a new instance of the fragment.  
The key of the pair would be `FRFragmentListener.ARGS_LISTENER_KEY` and its value would be the listener key.  
Finally, restore the listener in the fragment.

```kotlin
class SampleFragment : Fragment() {

    companion object {
        fun newInstance(fragmentListenerKey: String): SampleFragment =
            SampleFragment().apply {
                arguments = bundleOf(
                    FRFragmentListener.ARGS_LISTENER_KEY to fragmentListenerKey
                )
            }
    }

    private val listener: Listener? by lazy { restoreFragmentEventListener() }

}
```

### Show Dialog Fragment

Register a dialog callback and keep the return value as a private field in the subclass of `FRAppCompatActivity`.  
Then you can easily create and show the dialog fragment.

```kotlin
class SampleActivity : FRAppCompatActivity() {

    private val dialogCallbackKey = registerForDialogResult {
        // Write your result code here!
    }

    private fun showDialog() {
        FRDialogFragment.Builder(this).apply {
            setTitle(R.string.title)
            setMessage(R.string.message)
            setPositiveButton(R.string.ok)
            setNegativeButton(R.string.cancel)
            setCallbackKey(dialogCallbackKey)
        }.show()
    }

}
```

## Releases

Release notes are available [here](./release-notes/README.md).

## Documents

[A latest version](./docs/index.html) can be read witch written with Kdoc formats.  
Old versions are available from Assets of the past releases.

## License

This license is [Apache License 2.0](./LICENSE.txt).

## For Developers

Please read [Memos for Developers](./developers.md).