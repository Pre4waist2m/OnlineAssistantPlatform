import com.example.demo.controller.IndexControllerTest;
import com.example.demo.model.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({IndexControllerTest.class, ApplyCourseRepositoryTest.class,
        ClassAndStudentsRepositoryTest.class, ClassAndQuestionRepositoryTest.class, MessageSaveTest.class, QuestionOperatorTest.class,
saveClassInfoTest.class})
public class FieldApplicationTests {

}
