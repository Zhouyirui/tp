package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.ClassCode;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutorialclass.Schedule;
import seedu.address.model.tutorialclass.TutorialClass;
import seedu.address.model.tutorialgroup.TutorialGroup;

//TODO: Implement this skeleton class properly.
/**
 * Jackson-friendly version of {@link TutorialClass}.
 */
class JsonAdaptedTutorialClass {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tutorial Class's %s field is missing!";

    private final String classCode;
    private final String schedule;
    private final List<JsonAdaptedTutorialGroup> tutorialGroups = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTutorialClass} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedTutorialClass(@JsonProperty("classCode") String classCode,
                                    @JsonProperty("schedule") String schedule,
                                    @JsonProperty("tutorialGroups") List<JsonAdaptedTutorialGroup> tutorialGroups,
                                    @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.classCode = classCode;
        this.schedule = schedule;
        if (tutorialGroups != null) {
            this.tutorialGroups.addAll(tutorialGroups);
        }

        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code TutorialClass} into this class for Jackson use.
     */
    public JsonAdaptedTutorialClass(TutorialClass source) {
        classCode = source.getClassCode().toString();
        schedule = source.getSchedule().toString();
        tutorialGroups.addAll(source.getTutorialGroupsAsList().stream()
                .map(JsonAdaptedTutorialGroup::new)
                .collect(Collectors.toList()));
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public TutorialClass toModelType() throws IllegalValueException {
        final List<TutorialGroup> modelTutorialGroups = new ArrayList<>();
        for (JsonAdaptedTutorialGroup tutorialGroup : tutorialGroups) {
            modelTutorialGroups.add(tutorialGroup.toModelType());
        }

        final List<Tag> tutorialClassTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            tutorialClassTags.add(tag.toModelType());
        }

        if (classCode == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, String.class.getSimpleName()));
        }
        if (!ClassCode.isValidClassCode(classCode)) {
            throw new IllegalValueException(ClassCode.MESSAGE_CONSTRAINTS);
        }
        final ClassCode modelClassCode = new ClassCode(classCode);

        if (schedule == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, Schedule.class.getSimpleName())
            );
        }
        if (!Schedule.isValidSchedule(schedule)) {
            throw new IllegalValueException(Schedule.MESSAGE_CONSTRAINTS);
        }
        final Schedule modelSchedule = new Schedule(schedule);

        final Set<Tag> modelTags = new HashSet<>(tutorialClassTags);
        return new TutorialClass(modelClassCode, modelSchedule, modelTutorialGroups, modelTags);
    }

}
