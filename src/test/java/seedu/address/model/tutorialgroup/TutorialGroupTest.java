package seedu.address.model.tutorialgroup;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CLASSCODE_G102;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPNAME_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUPTYPE_OP2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.testutil.TypicalTutorialGroups.TUT_01;
import static seedu.address.testutil.TypicalTutorialGroups.TUT_02;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.TutorialGroupBuilder;

class TutorialGroupTest {

    @Test
    public void isSameTutorialGroup() {
        // same object -> returns true
        assertTrue(TUT_01.isSameTutorialGroup(TUT_01));

        // null -> returns false
        assertFalse(TUT_01.isSameTutorialGroup(null));

        // same class code, all other attributes different -> returns false
        TutorialGroup editedTut01 = new TutorialGroupBuilder(TUT_01).withGroupName(VALID_GROUPNAME_2)
                .withGroupType(VALID_GROUPTYPE_OP2).build();
        assertFalse(TUT_01.isSameTutorialGroup(editedTut01));

        // same group name, all other attributes different -> returns false
        editedTut01 = new TutorialGroupBuilder(TUT_01).withClassCode(VALID_CLASSCODE_G102)
                .withGroupType(VALID_GROUPTYPE_OP2).build();
        assertFalse(TUT_01.isSameTutorialGroup(editedTut01));

        // same group type, all other attributes different -> returns false
        editedTut01 = new TutorialGroupBuilder(TUT_01).withClassCode(VALID_CLASSCODE_G102)
                .withGroupName(VALID_GROUPNAME_2).build();
        assertFalse(TUT_01.isSameTutorialGroup(editedTut01));

        // different class code, all other attributes same -> returns false
        editedTut01 = new TutorialGroupBuilder(TUT_01).withClassCode(VALID_NAME_BOB).build();
        assertFalse(TUT_01.isSameTutorialGroup(editedTut01));

        // different group name, all other attributes same -> returns false
        editedTut01 = new TutorialGroupBuilder(TUT_01).withGroupName(VALID_GROUPNAME_2).build();
        assertFalse(TUT_01.isSameTutorialGroup(editedTut01));

        // different group type, all other attributes same -> returns false
        editedTut01 = new TutorialGroupBuilder(TUT_01).withGroupType(VALID_GROUPTYPE_OP2).build();
        assertFalse(TUT_01.isSameTutorialGroup(editedTut01));
    }

    @Test
    public void equals() {
        // same values -> returns true
        TutorialGroup tut01Copy = new TutorialGroupBuilder(TUT_01).build();
        assertTrue(TUT_01.equals(tut01Copy));

        // same object -> returns true
        assertTrue(TUT_01.equals(TUT_01));

        // null -> returns false
        assertFalse(TUT_01.equals(null));

        // different type -> returns false
        assertFalse(TUT_01.equals(5));

        // different groups -> returns false
        assertFalse(TUT_01.equals(TUT_02));

        // different class code -> returns false
        TutorialGroup editedTut01 = new TutorialGroupBuilder(TUT_01).withClassCode(VALID_CLASSCODE_G102).build();
        assertFalse(TUT_01.equals(editedTut01));

        // different group name -> returns false
        editedTut01 = new TutorialGroupBuilder(TUT_01).withGroupName(VALID_GROUPNAME_2).build();
        assertFalse(TUT_01.equals(editedTut01));

        // different email -> returns false
        editedTut01 = new TutorialGroupBuilder(TUT_01).withGroupType(VALID_GROUPTYPE_OP2).build();
        assertFalse(TUT_01.equals(editedTut01));
    }
}
