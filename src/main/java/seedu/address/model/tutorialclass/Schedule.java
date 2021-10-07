package seedu.address.model.tutorialclass;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;
/**
 *
 *
 */
public class Schedule {
    public static final String MESSAGE_CONSTRAINTS =
            "Schedules should follow the format 'day-of-the-week starttime to endtime', "
                    + "with different classes seperated by commas. \n"
                    + "only contain alphanumeric characters, spaces and -'s"
                    + "between start and end time and should not be blank";

    private static final String TIME_REGEX = "[^(1[0-2]|0?[1-9]):([0-5]?[0-9])(●?[AP]M)?$]";
    private static final String VALIDATION_REGEX = "[\\p{Alnum} ]" + TIME_REGEX
            + "[ \\p{Alnum} ]" + TIME_REGEX;

    private final String schedule;

    /**
     * Constructor for Schedule class.
     * @param schedule String containing class schedule.
     */
    public Schedule(String schedule) {
        requireNonNull(schedule);
        checkArgument(isValidSchedule(schedule), MESSAGE_CONSTRAINTS);
        this.schedule = schedule;
    }

    /**
     * Checks validity of input string.
     *
     * @param test Test string.
     * @return validity of input schedule string.
     */
    public static boolean isValidSchedule(String test) {
        String[] segments = test.split(",");
        for (String segment : segments) {
            if (!test.matches(VALIDATION_REGEX)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return schedule;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Schedule // instanceof handles nulls
                && schedule.equals(((Schedule) other).schedule)); // state check
    }

    @Override
    public int hashCode() {
        return this.schedule.hashCode();
    }
}
