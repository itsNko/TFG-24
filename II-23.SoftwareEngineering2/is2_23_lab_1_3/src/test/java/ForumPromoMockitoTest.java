import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import bl.ForumBL;
import db.ForumDAOInterface;
import domain.Article;
import domain.User;
import exceptions.NullParameterException;
import exceptions.QuantityLessThan1Exception;
import exceptions.UserNotFoundException;

class ForumPromoMockitoTest {
    static ForumDAOInterface forumDAO;
    static ForumBL sut; // SUT: System Under Test
    String id, name, tel;
    User user;
    Article article;
    int quantity;

    @BeforeAll
    static void setUp() {
        forumDAO = Mockito.mock(ForumDAOInterface.class);
        sut = new ForumBL(forumDAO);
    }

    @Test
    @DisplayName("Test 0: Initial test")
    void test0() {
        assertNull(forumDAO.getUserDAO("1234567A"));
    }

    /////////////
    // addUser //
    /////////////

    @Test
    @DisplayName("addUser Function Dynamic Integration Test: Case 1")
    void addUserDITest1() {
        id = "12345678A";
        name = "John Doe";
        tel = null;
        Mockito.doReturn(false).when(forumDAO).existUserDAO(Mockito.anyString());
        Mockito.doReturn(new User(id, name, tel)).when(forumDAO).getUserDAO(Mockito.anyString());
        try {
            user = sut.addUser(id, name, tel);
            assertEquals(id, user.getId());
            assertEquals(0, user.getName().compareTo(name));
            assertNull(user.getTelephone());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    @DisplayName("addUser Function Dynamic Integration Test: Case 2")
    void addUserDITest2() {
        id = null;
        name = "John Doe";
        tel = null;
        assertThrows(NullParameterException.class, () -> sut.addUser(id, name, tel));
    }

    @Test
    @DisplayName("addUser Function Dynamic Integration Test: Case 3")
    void addUserDITest3() {
        id = "12345678A";
        name = null;
        tel = "000000000";
        assertThrows(NullParameterException.class, () -> sut.addUser(id, name, tel));
    }

    @Test
    @DisplayName("addUser Function Dynamic Integration Test: Case 4")
    void addUserDITest4() {
        id = "12345678A";
        name = "John Doe";
        tel = "000000000";
        Mockito.doReturn(true).when(forumDAO).existUserDAO(Mockito.anyString());

        assertThrows(UserNotFoundException.class, () -> sut.addUser(id, name, tel));
    }

    ///////////////
    // addBasket //
    ///////////////

    @Test
    @DisplayName("addBasket Function Dynamic Integration Black Box Test: Case 1")
    void addBasketDIBTest1() {
        user = new User("12345678A", "John Doe", "000000000");
        article = new Article("art1", "Mock Article", 1, false, 1);
        quantity = 1;

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        ArgumentCaptor<Article> articleCaptor = ArgumentCaptor.forClass(Article.class);
        ArgumentCaptor<Integer> quantityCaptor = ArgumentCaptor.forClass(Integer.class);

        try {
            sut.addBasket(user, article, quantity);

            Mockito.verify(forumDAO, Mockito.times(1)).addBasketDAO(userCaptor.capture(), articleCaptor.capture(),
                    quantityCaptor.capture());

            assertEquals(user, userCaptor.getValue());
            assertEquals(article, articleCaptor.getValue());
            assertEquals(quantity, quantityCaptor.getValue());
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    @DisplayName("addBasket Function Dynamic Integration Black Box Test: Case 2")
    void addBasketDIBTest2() {
        user = null;
        article = new Article("art1", "Mock Article", 1, false, 1);
        quantity = 1;

        assertThrows(NullParameterException.class, () -> sut.addBasket(user, article, quantity));
    }

    @Test
    @DisplayName("addBasket Function Dynamic Integration Black Box Test: Case 2")
    void addBasketDIBTest3() {
        user = new User("12345678A", "John Doe", "000000000");
        article = null;
        quantity = 1;

        assertThrows(NullParameterException.class, () -> sut.addBasket(user, article, quantity));
    }

    @Test
    @DisplayName("addBasket Function Dynamic Integration Black Box Test: Case 4")
    void addBasketDIBTest4() {
        user = new User("12345678A", "John Doe", "000000000");
        article = new Article("art1", "Mock Article", 1, false, 1);
        quantity = 0;

        assertThrows(QuantityLessThan1Exception.class, () -> sut.addBasket(user, article, quantity));
    }
}
