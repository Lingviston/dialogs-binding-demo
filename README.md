# Demo project for [Modile People: Open Android Meetup](https://events.epam.com/events/mobile-people-open-android-meetup/talks/12259)
## Dialogs
The demo project contains all three solutions for dialogs show up. You can run any of them via clicking "Solution #" button. Notice, that for "Solution 3" demo contains both cases with embed view and `DialogShowingView`.

For instance you can start "Solution 1" like this:

<img src="/demo_gifs/dialog_solution_1.gif" width="288" height="512" />

Or you can start "Solution 3" for both scenarios like this:

<img src="/demo_gifs/dialog_solution_3.gif" width="288" height="512" />

To simulate "Duplicated state" problem for both solutions 1 and 2 you can follow the next steps:
1. Open the required solution in the demo application;
2. Press "Home" button;
3. Press "Terminate Application" in Logcat window in Android Studio (this will kill the app process, make sure you select your device and process in Logcat dropdowns at top);
4. Get back to the application through the "Recent apps".

## Toasts
The demo project contains demo of the "State problem" for `ToastView`. You can check it following the steps from the gifs below.

With problem:

<img src="/demo_gifs/toasts_state_problem.gif" width="288" height="512" />

With problem fixed:

<img src="/demo_gifs/toasts_state_problem_fixed.gif" width="288" height="512" />
