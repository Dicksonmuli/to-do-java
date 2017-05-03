import java.time.LocalDateTime;
import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class TaskTest {

	@Rule
  public DatabaseRule database = new DatabaseRule();

	// creating an instance of Task successfully
	@Test
	public void Task_instantiatesCorrectly_true() {
		Task myTask = new Task("Mow the lawn", 1);
		assertEquals(true, myTask instanceof Task);
	}
	// assigning each task a description and then retrieve it
	@Test
	public void Task_instantiatesWithDescription_String() {
		Task myTask = new Task("Mow the lawn", 1);
		assertEquals("Mow the lawn", myTask.getDescription());
	}
	//equate to false if a task if not completed
	@Test
	public void isCompleted_isFalseAfterInstantiation_false() {
		Task myTask = new Task("Mow the lawn", 1);
		assertFalse(myTask.isCompleted());
	}
	//instanciate with time
	@Test
	public void getCreatedAt_instantiatesWithCurrentTime_today() {
		Task myTask = new Task("Mow the lawn", 1);
		assertEquals(LocalDateTime.now().getDayOfWeek(), myTask.getCreatedAt().getDayOfWeek());
	}

	@Test
	public void clear_emptiesAllTasksFromArrayList_0() {
  Task myTask = new Task("Mow the lawn", 1);
  assertEquals(Task.all().size(), 0);
}

@Test
public void getId_tasksInstantiateWithAnID_1() {
  // Task.clear();  // Remember, the test will fail without this line! We need to empty leftover Tasks from previous tests!(we dont need it with database)
  Task myTask = new Task("Mow the lawn", 1);
	myTask.save();
  assertTrue(myTask.getId() > 0);
}

@Test
public void find_returnsTaskWithSameId_secondTask() {
	Task firstTask = new Task("Mow the lawn", 1);
	firstTask.save();
  Task secondTask = new Task("Buy groceries", 2);
	secondTask.save();
  assertEquals(Task.find(secondTask.getId()), secondTask);
}

//returns true
@Test
public void equals_returnsTrueIfDescriptionsAretheSame() {
	Task firstTask = new Task("Mow the lawn", 1);
	Task secondTask = new Task("Mow the lawn", 1);
	assertTrue(firstTask.equals(secondTask));
}

 	@Test
	public void save_returnsTrueIfDescriptionsAretheSame() {
		Task myTask = new Task("Mow the lawn", 1);
		myTask.save();
		assertTrue(Task.all().get(0).equals(myTask));
	}

	@Test
  public void all_returnsAllInstancesOfTask_true() {
    Task firstTask = new Task("Mow the lawn", 1);
    firstTask.save();
    Task secondTask = new Task("Buy groceries", 2);
    secondTask.save();
    assertEquals(true, Task.all().get(0).equals(firstTask));
    assertEquals(true, Task.all().get(1).equals(secondTask));
  }

	@Test
	public void save_assignsIdToObject() {
	  Task myTask = new Task("Mow the lawn", 1);
	  myTask.save();
	  Task savedTask = Task.all().get(0);
	  assertEquals(myTask.getId(), savedTask.getId());
	}

	@Test
  public void getId_tasksInstantiateWithAnID() {
    Task myTask = new Task("Mow the lawn", 1);
    myTask.save();
    assertTrue(myTask.getId() > 0);
  }

	@Test
	public void save_savesCategoryIdIntoDB_true() {
	 Category myCategory = new Category("Household chores");
		myCategory.save();
		Task myTask = new Task("Mow the lawn", myCategory.getId());
		myTask.save();
		Task savedTask = Task.find(myTask.getId());
		assertEquals(savedTask.getCategoryId(), myCategory.getId());
	}
	//update  tasks
	@Test
	public void update_updatesTaskDescription_true() {
		Task myTask = new Task("Mow the lawn", 1);
		myTask.save();
		myTask.update("Take a nap");
		assertEquals("Take a nap", Task.find(myTask.getId()).getDescription());
	}
	@Test
public void delete_deletesTask_true() {
  Task myTask = new Task("Mow the lawn", 1);
  myTask.save();
  int myTaskId = myTask.getId();
  myTask.delete();
  assertEquals(null, Task.find(myTaskId));
}
}
