package pink.madis.perftesting.sample;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

class RepeatRule implements TestRule {
    private final int repeats;
    private int counter;

    RepeatRule(int repeats) {
        if (repeats <= 0) {
            throw new IllegalArgumentException("Need at least 1 repeat when adding a repeat rule");
        }
        this.repeats = repeats;
    }

    @Override
    public Statement apply(final Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                for (counter = 0; counter < repeats; counter++) {
                    base.evaluate();
                }
            }
        };
    }

    int getRepeatNumber() {
        return counter;
    }
}
