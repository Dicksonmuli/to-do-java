import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.Arrays;

public class CategoryTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();
  
  @Test
  public void category_instantiatesCorrectly_true() {
    Category testCategory = new Category("Home");
    assertEquals(true, testCategory instanceof Category);
  }

	@Test
 public void getName_categoryInstantiatesWithName_Home() {
	 Category testCategory = new Category("Home");
	 assertEquals("Home", testCategory.getName());
 }

	@Test
 public void clear_emptiesAllCategoriesFromList_0() {
	 Category testCategory = new Category("Home");
	 assertEquals(Category.all().size(), 0);
 }
	 @Test
 public void find_returnsCategoryWithSameId_secondCategory() {
	 Category firstCategory = new Category("Home");
   firstCategory.save();
	 Category secondCategory = new Category("Work");
   secondCategory.save();
	 assertEquals(Category.find(secondCategory.getId()), secondCategory);
 }

@Test
public void find_returnsNullWhenNotaskFound_null() {
  assertTrue(Category.find(999) == null);
}

@Test
      public void equals_returnsTrueIfNamesAretheSame() {
        Category firstCategory = new Category("Household chores");
        Category secondCategory = new Category("Household chores");
        assertTrue(firstCategory.equals(secondCategory));
      }


  @Test
     public void save_savesIntoDatabase_true() {
       Category myCategory = new Category("Household chores");
       myCategory.save();
       assertTrue(Category.all().get(0).equals(myCategory));
  }
  @Test
      public void all_returnsAllInstancesOfCategory_true() {
        Category firstCategory = new Category("Home");
        firstCategory.save();
        Category secondCategory = new Category("Work");
        secondCategory.save();
        assertEquals(true, Category.all().get(0).equals(firstCategory));
        assertEquals(true, Category.all().get(1).equals(secondCategory));
      }
      @Test
    public void getId_categoriesInstantiateWithAnId_1() {
      Category testCategory = new Category("Home");
      testCategory.save();
      assertTrue(testCategory.getId() > 0);
    }

    @Test
      public void getTasks_retrievesALlTasksFromDatabase_tasksList() {
        Category myCategory = new Category("Household chores");
        myCategory.save();
        Task firstTask = new Task("Mow the lawn", myCategory.getId());
        firstTask.save();
        Task secondTask = new Task("Do the dishes", myCategory.getId());
        secondTask.save();
        Task[] tasks = new Task[] { firstTask, secondTask };
        assertTrue(myCategory.getTasks().containsAll(Arrays.asList(tasks)));
      }

}
