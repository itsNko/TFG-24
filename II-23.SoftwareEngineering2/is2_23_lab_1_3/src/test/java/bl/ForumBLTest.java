package bl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentCaptor;

import db.ForumDAOInterface;
import domain.Article;
import domain.Purchase;
import domain.PurchasedArticle;
import domain.User;
import exceptions.NullParameterException;
import exceptions.UserNotFoundException;

class ForumBLTest {
    ForumDAOInterface forumDAO;
    ForumBL sut;

    @BeforeEach
    void setUp() {
        forumDAO = mock(ForumDAOInterface.class);
        sut = new ForumBL(forumDAO);
    }

    @Test
    @DisplayName("Test getBonus with valid user and valid purchases")
    void testGetBonusValidUserValidPurchases() throws Exception {
        // Test parameters
        String id = "12345678A";
        String name = "John Doe";
        String telephone = "123456789";
        User user = new User(id, name, telephone);
        float expectedBonus = 10.0f;

        // Configure mock
        when(forumDAO.getUserDAO(id)).thenReturn(user);
        when(forumDAO.getPurchasesDAO(eq(user), any(Date.class), any(Date.class))).thenReturn(getMockedPurchases());

        // Call sut
        float actualBonus = sut.getBonus(id);

        // Verify results
        assertEquals(expectedBonus, actualBonus);
    }

    @Test
    @DisplayName("Test getBonus with invalid user")
    void testGetBonusInvalidUser() throws Exception {
        // Test parameters
        String id = "12345678A";

        // Configure mock
        when(forumDAO.getUserDAO(id)).thenReturn(null);

        // Call sut and verify exception
        assertThrows(Exception.class, () -> sut.getBonus(id));
    }

    @Test
    @DisplayName("Test getBonus with user not registered telephone")
    void testGetBonusUserNotRegisteredTelephone() throws Exception {
        // Test parameters
        String id = "12345678A";
        String name = "John Doe";
        String telephone = null;
        User user = new User(id, name, telephone);

        // Configure mock
        when(forumDAO.getUserDAO(id)).thenReturn(user);

        // Call sut and verify exception
        assertThrows(Exception.class, () -> sut.getBonus(id));
    }

    @ParameterizedTest
    @ValueSource(strings = { "12345678A", "98765432B" })
    @DisplayName("Test getBonus with invalid ID")
    void testGetBonusInvalidID(String id) throws Exception {
        // Call sut and verify exception
        assertThrows(Exception.class, () -> sut.getBonus(id));
    }

    @ParameterizedTest
    @CsvSource({ "01/09/2022, 06/12/2022, 100.0", "01/09/2022, 06/12/2022, 200.0" })
    @DisplayName("Test getBonus with different purchase sums")
    void testGetBonusDifferentPurchaseSums(String firstDateStr, String lastDateStr, float expectedBonus)
            throws Exception {
        // Test parameters
        String id = "12345678A";
        String name = "John Doe";
        String telephone = "123456789";
        User user = new User(id, name, telephone);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date firstDate = sdf.parse(firstDateStr);
        Date lastDate = sdf.parse(lastDateStr);

        // Configure mock
        when(forumDAO.getUserDAO(id)).thenReturn(user);
        when(forumDAO.getPurchasesDAO(eq(user), eq(firstDate), eq(lastDate))).thenReturn(getMockedPurchases());

        // Call sut
        float actualBonus = sut.getBonus(id);

        // Verify results
        assertEquals(expectedBonus, actualBonus);
    }

    @Test
    @DisplayName("Test getBonus with no purchases")
    void testGetBonusNoPurchases() throws Exception {
        // Test parameters
        String id = "12345678A";
        String name = "John Doe";
        String telephone = "123456789";
        User user = new User(id, name, telephone);
        float expectedBonus = 0.0f;

        // Configure mock
        when(forumDAO.getUserDAO(id)).thenReturn(user);
        when(forumDAO.getPurchasesDAO(eq(user), any(Date.class), any(Date.class)))
                .thenReturn(getEmptyMockedPurchases());

        // Call sut
        float actualBonus = sut.getBonus(id);

        // Verify results
        assertEquals(expectedBonus, actualBonus);
    }

    @Test
    @DisplayName("Test getBonus with outlet articles")
    void testGetBonusOutletArticles() throws Exception {
        // Test parameters
        String id = "12345678A";
        String name = "John Doe";
        String telephone = "123456789";
        User user = new User(id, name, telephone);
        float expectedBonus = 0.0f;

        // Configure mock
        when(forumDAO.getUserDAO(id)).thenReturn(user);
        when(forumDAO.getPurchasesDAO(eq(user), any(Date.class), any(Date.class)))
                .thenReturn(getMockedPurchasesWithOutletArticles());

        // Call sut
        float actualBonus = sut.getBonus(id);

        // Verify results
        assertEquals(expectedBonus, actualBonus);
    }

    @Test
    @DisplayName("Test getBonus with sumPurchases <= 30")
    void testGetBonusSumPurchasesLessThan30() throws Exception {
        // Test parameters
        String id = "12345678A";
        String name = "John Doe";
        String telephone = "123456789";
        User user = new User(id, name, telephone);
        float expectedBonus = 0.0f;

        // Configure mock
        when(forumDAO.getUserDAO(id)).thenReturn(user);
        when(forumDAO.getPurchasesDAO(eq(user), any(Date.class), any(Date.class)))
                .thenReturn(getMockedPurchasesWithSumPurchases(20.0f));

        // Call sut
        float actualBonus = sut.getBonus(id);

        // Verify results
        assertEquals(expectedBonus, actualBonus);
    }

    @Test
    @DisplayName("Test getBonus with sumPurchases > 30 and <= 288")
    void testGetBonusSumPurchasesBetween30And288() throws Exception {
        // Test parameters
        String id = "12345678A";
        String name = "John Doe";
        String telephone = "123456789";
        User user = new User(id, name, telephone);
        float expectedBonus = 5.2075f;

        // Configure mock
        when(forumDAO.getUserDAO(id)).thenReturn(user);
        when(forumDAO.getPurchasesDAO(eq(user), any(Date.class), any(Date.class)))
                .thenReturn(getMockedPurchasesWithSumPurchases(50.0f));

        // Call sut
        float actualBonus = sut.getBonus(id);

        // Verify results
        assertEquals(expectedBonus, actualBonus);
    }

    @Test
    @DisplayName("Test getBonus with sumPurchases > 288")
    void testGetBonusSumPurchasesGreaterThan288() throws Exception {
        // Test parameters
        String id = "12345678A";
        String name = "John Doe";
        String telephone = "123456789";
        User user = new User(id, name, telephone);
        float expectedBonus = 50.0f;

        // Configure mock
        when(forumDAO.getUserDAO(id)).thenReturn(user);
        when(forumDAO.getPurchasesDAO(eq(user), any(Date.class), any(Date.class)))
                .thenReturn(getMockedPurchasesWithSumPurchases(300.0f));

        // Call sut
        float actualBonus = sut.getBonus(id);

        // Verify results
        assertEquals(expectedBonus, actualBonus);
    }

    private Iterator<Purchase> getMockedPurchases() {
        Purchase purchase1 = new Purchase();
        Article article1 = new Article("article1", "Article 1", 10, false, 2);
        purchase1.addBasket(article1, 1);

        Purchase purchase2 = new Purchase();
        Article article3 = new Article("article3", "Article 3", 30, false, 3);
        purchase2.addBasket(article3, 3);

        return new Iterator<Purchase>() {
            private Purchase[] purchases = { purchase1, purchase2 };
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < purchases.length;
            }

            @Override
            public Purchase next() {
                return purchases[index++];
            }
        };
    }

    private Iterator<Purchase> getEmptyMockedPurchases() {
        return new Iterator<Purchase>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public Purchase next() {
                return null;
            }
        };
    }

    private Iterator<Purchase> getMockedPurchasesWithOutletArticles() {
        Purchase purchase1 = new Purchase();
        Article article1 = new Article("article1", "Article 1", 10, true, 2);
        purchase1.addBasket(article1, 2);
        Article article2 = new Article("article2", "Article 2", 20, true, 1);
        purchase1.addBasket(article2, 1);

        Purchase purchase2 = new Purchase();
        Article article3 = new Article("article3", "Article 3", 30, true, 3);
        purchase2.addBasket(article3, 3);

        return new Iterator<Purchase>() {
            private Purchase[] purchases = { purchase1, purchase2 };
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < purchases.length;
            }

            @Override
            public Purchase next() {
                return purchases[index++];
            }
        };
    }

    private Iterator<Purchase> getMockedPurchasesWithSumPurchases(float sumPurchases) {
        Purchase purchase1 = new Purchase();
        Article article1 = new Article("article1", "Article 1", 10, false, 2);
        purchase1.addBasket(article1, 2);
        Article article2 = new Article("article2", "Article 2", 20, false, 1);
        purchase1.addBasket(article2, 1);
    
        Purchase purchase2 = new Purchase();
        Article article3 = new Article("article3", "Article 3", 30, false, 3);
        purchase2.addBasket(article3, 3);
    
        return new Iterator<Purchase>() {
            private Purchase[] purchases = { purchase1, purchase2 };
            private int index = 0;
    
            @Override
            public boolean hasNext() {
                return index < purchases.length;
            }
    
            @Override
            public Purchase next() {
                return purchases[index++];
            }
        };
    }
}