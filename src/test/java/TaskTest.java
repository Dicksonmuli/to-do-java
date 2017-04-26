import java.time.LocalDateTime;
import org.junit.*;
import static org.junit.Assert.*;

public class TaskTest {
	// creating an instance of Task successfully
	@Test
	public void Task_instantiatesCorrectly_true() {
		Task myTask = new Task("Mow the lawn");
		assertEquals(true, myTask instanceof Task);
	}
	// assigning each task a description and then retrieve it
	@Test
	public void Task_instantiatesWithDescription_String() {
		Task myTask = new Task("Mow the lawn");
		assertEquals("Mow the lawn", myTask.getDescription());
	}
	//equate to false if a task if not completed
	@Test
	public void isCompleted_isFalseAfterInstantiation_false() {
		Task myTask = new Task("Mow the lawn");
		assertFalse(myTask.isCompleted());
	}
	//instanciate with time
	@Test
	public void getCreatedAt_instantiatesWithCurrentTime_today() {
		Task myTask = new Task("Mow the lawn");
		assertEquals(LocalDateTime.now().getDayOfWeek(), myTask.getCreatedAt().getDayOfWeek());
	}
	//returns all instances of task
	@Test
	public void all_returnsAllInstancesOfTask_true() {
		Task firtTask = new Task("Mow the lawn");
		Task secondTask = new Task("Buy groceries");
		assertEquals(true, Task.all().contains(firtTask));
		assertEquals(true, Task.all().contains(secondTask));
	}

	@Test
	public void clear_emptiesAllTasksFromArrayList_0() {
  Task myTask = new Task("Mow the lawn");
  Task.clear();
  assertEquals(Task.all().size(), 0);
}

@Test
public void getId_tasksInstantiateWithAnID_1() {
  Task.clear();  // Remember, the test will fail without this line! We need to empty leftover Tasks from previous tests!
  Task myTask = new Task("Mow the lawn");
  assertEquals(1, myTask.getId());
}

@Test
public void find_returnsTaskWithSameId_secondTask() {
	Task firstTask = new Task("Mow the lawn");
  Task secondTask = new Task("Buy groceries");
  assertEquals(Task.find(secondTask.getId()), secondTask);
}
}

}
