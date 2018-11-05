package ca.mcgill.ecse321.eventregistration;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.EditText;

import com.robotium.solo.Solo;

public class RobotiumTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;

    private static final String LAUNCHER_ACTIVITY_FULL_CLASSNAME = "ca.mcgill.ecse321.eventregistration.MainActivity";

    private static Class<?> launcherActivityClass;

    static {
        try {
            launcherActivityClass = Class.forName(LAUNCHER_ACTIVITY_FULL_CLASSNAME);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public RobotiumTest() throws ClassNotFoundException {
        super((Class<MainActivity>) launcherActivityClass);
    }

    private String participantName;
    private String testParticipantName = "TestParticipant";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        solo = new Solo(getInstrumentation());

        int uniqueId = 1586; // Ensure uniqueness of name
        participantName = "Participant" + uniqueId;
        getActivity();
    }

    @Override
    public void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

//    public void testRegisterSucceeds() throws InterruptedException {
//        solo.waitForActivity("MainActivity", 2000);
//
//        EditText editText = solo.getEditText("Who?");
//        solo.enterText(editText,participantName);
//        solo.clickOnText(solo.getString(R.string.newparticipant_button));
//
//        boolean errorTextFound = solo.waitForText("exception", 1, 5000);
//        assertFalse(errorTextFound);
//    }
//
//    public void testRegisterFails() throws InterruptedException {
//        solo.waitForActivity("MainActivity", 2000);
//
//        EditText editText = solo.getEditText("Who?");
//        solo.enterText(editText,testParticipantName);
//        solo.clickOnText(solo.getString(R.string.newparticipant_button));
//
//        boolean errorTextFound = solo.waitForText("exception", 1, 5000);
//        assertTrue(errorTextFound);
//    }

    public void testListRefreshes() {
        solo.clickOnText(solo.getString(R.string.refresh_participant_list));

        boolean textFound = solo.waitForText(testParticipantName, 1, 5000, true);

        assertTrue(textFound);
    }
}