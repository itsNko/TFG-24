package iloveyouboss;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProfileTest {
    static Profile profile;
    static Question question;
    static Answer profileAnswer, criteriaAnswer;
    static Criteria criteria;
    Criterion criterion;

    @BeforeAll
    static void BeforeAll() {
        profile = new Profile("Bull Hockey, Inc.");
    }

    @BeforeEach
    void BeforeEach() {
        question = new BooleanQuestion(1, "Got bonuses?");
        profileAnswer = new Answer(question, Bool.FALSE);
        profile.add(profileAnswer);

        criteria = new Criteria();
    }

    @Test
    void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
        criteriaAnswer = new Answer(question, Bool.TRUE);
        criterion = new Criterion(criteriaAnswer, Weight.MustMatch);
        criteria.add(criterion);
        boolean matches = profile.matches(criteria);
        assertFalse(matches);
    }

    @Test
    void matchAnswersTrueForAnyDontCareCriteria() {
        profileAnswer = new Answer(question, Bool.FALSE);
        profile.add(profileAnswer);
        criteriaAnswer = new Answer(question, Bool.TRUE);
        criterion = new Criterion(criteriaAnswer, Weight.DontCare);
        criteria.add(criterion);
        boolean matches = profile.matches(criteria);
        assertTrue(matches);
    }
}
