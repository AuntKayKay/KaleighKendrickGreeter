package com.mycompany.greeter;

import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Kaleigh on 3/1/2016.
 */
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

        // Verify greet message
        // ----------------------

        TextView greetMessage = (TextView) activity.findViewById(R.id.message_text_view);
        String actualText = greetMessage.getText().toString();
        assertEquals("Hello, Jake!", actualText);
    }
}