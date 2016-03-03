package com.mycompany.greeter;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivityTests extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTests() {
        super(MainActivity.class);
    }

    //The test verifies that we configured our app and test code correctly.
    public void testActivityExists() {
        MainActivity activity = getActivity();
        assertNotNull(activity);
    }

    public void testGreet() {
        MainActivity activity = getActivity();
        this.greetingHelper();

        // Verify greet message
        // ----------------------

        TextView greetMessage = (TextView) activity.findViewById(R.id.message_text_view);
        String actualText = greetMessage.getText().toString();
        assertEquals("Hello, Jake!", actualText);
    }

        //Checks that the reverse button is disabled when the activity starts.
        public void testReverseDisabled() {
            MainActivity activity = getActivity();
            Button reverseButton =
                    (Button) activity.findViewById(R.id.reverseButton);

            assertEquals(false, reverseButton.isEnabled());
        }

        // Checks that the reverse button is enabled when the greet button is clicked.
        public void testReverseEnabled() {
            MainActivity activity = getActivity();

            Button greetButton =
                    (Button) activity.findViewById(R.id.greet_button);

            TouchUtils.clickView(this, greetButton);

            Button reverseButton =
                    (Button) activity.findViewById(R.id.reverseButton);

            assertEquals(true, reverseButton.isEnabled());
        }

        //Checks that the text in the TextView is reversed after both buttons have been clicked.
        public void testReverse() {
            MainActivity activity = getActivity();
            this.greetingHelper();

            //Tap "Reverse" button
            // ----------------------

            Button reverseButton =
                    (Button) activity.findViewById(R.id.reverseButton);

            TouchUtils.clickView(this, reverseButton);

            // Verify greet message
            // ----------------------

            TextView greetMessage = (TextView) activity.findViewById(R.id.message_text_view);
            String actualText = greetMessage.getText().toString();
            assertEquals("!ekaJ ,olleH", actualText);
        }

    //Gets the input, taps the "Greet" button.
    private void greetingHelper(){
        MainActivity activity = getActivity();

        // Type name in text input
        // ----------------------

        final EditText nameEditText =
                (EditText) activity.findViewById(R.id.greet_edit_text);

        // Send string input value
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                nameEditText.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("Jake");
        getInstrumentation().waitForIdleSync();

        // Tap "Greet" button
        // ----------------------

        Button greetButton =
                (Button) activity.findViewById(R.id.greet_button);

        TouchUtils.clickView(this, greetButton);

    }
}