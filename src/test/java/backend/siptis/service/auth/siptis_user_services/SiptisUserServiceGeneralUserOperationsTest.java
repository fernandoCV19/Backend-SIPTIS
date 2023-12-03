package backend.siptis.service.auth.siptis_user_services;

import backend.siptis.commons.ServiceMessage;
import backend.siptis.model.repository.auth.SiptisUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class SiptisUserServiceGeneralUserOperationsTest {
    @Autowired
    private SiptisUserServiceGeneralUserOperations siptisUserServiceGeneralUserOperations;
    @Autowired
    private SiptisUserRepository siptisUserRepository;

    private void clearDatabase() {
        siptisUserRepository.deleteAll();
    }

    @Test
    @DisplayName("test get project by non existing id")
    void givenUserIDWhenGetProjectByIdThenNull() {
        assertNull(siptisUserServiceGeneralUserOperations.getProjectById(10000L));
    }

    @Test
    @DisplayName("test get project by id success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenUserIDWhenGetProjectByIdThenNotNull() {
        assertNotNull(siptisUserServiceGeneralUserOperations.getProjectById(100L));
    }

    @Test
    @DisplayName("test get all users")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenSiptisUsersWhenGetAllUsersThenServiceMessageOK() {
        assertEquals(ServiceMessage.OK, siptisUserServiceGeneralUserOperations.getAllUsers().getServiceMessage());
    }

    @Test
    @DisplayName("test get all users")
    void givenSiptisUsersWhenGetAllUsersThenServiceMessageNOT_FOUND() {
        clearDatabase();
        assertEquals(ServiceMessage.NOT_FOUND, siptisUserServiceGeneralUserOperations.getAllUsers().getServiceMessage());
    }

    @Test
    @DisplayName("test get user personal information")
    void givenBadUserIdWhenGetUserPersonalInformationThenServiceMessageNOT_FOUND() {
        assertEquals(ServiceMessage.NOT_FOUND, siptisUserServiceGeneralUserOperations.getUserPersonalInformation(123123L).getServiceMessage());
    }

    @Test
    @DisplayName("test get user personal information")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenBadUserIdWhenGetUserPersonalInformationThenServiceMessageOK() {
        assertEquals(ServiceMessage.OK, siptisUserServiceGeneralUserOperations.getUserPersonalInformation(100L).getServiceMessage());
    }

    @Test
    @DisplayName("test get user areas by id not found")
    void givenUserIdWhenGetUserAreasByIdThenServiceMessageNOT_FOUND() {
        assertEquals(ServiceMessage.NOT_FOUND, siptisUserServiceGeneralUserOperations.getUserAreasById(123123L).getServiceMessage());
    }

    @Test
    @DisplayName("test get user areas by id success")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenUserIdWhenGetUserAreasByIdThenServiceMessageOK() {
        assertEquals(ServiceMessage.OK, siptisUserServiceGeneralUserOperations.getUserAreasById(100L).getServiceMessage());
    }

    @Test
    @DisplayName("test get user list")
    void givenUserIdWhenGetUserListThenServiceMessageOK() {
        assertEquals(ServiceMessage.OK, siptisUserServiceGeneralUserOperations.getUserList("", "ADMIN", Pageable.ofSize(12)).getServiceMessage());
    }

    @Test
    @DisplayName("test get normal user list")
    void givenUserIdWhenGetNormalUserListThenServiceMessageOK() {
        assertEquals(ServiceMessage.OK, siptisUserServiceGeneralUserOperations.getNormalUserList("", Pageable.ofSize(12)).getServiceMessage());
    }
/*
    @Test
    @DisplayName("test get personal activities")
    @Sql(scripts = {"/testDB.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    void givenUserIdWhenGetPersonalActivitiesThenServiceMessageOK() {
        assertEquals(ServiceMessage.OK, siptisUserServiceGeneralUserOperations.getPersonalActivities(100L, Pageable.ofSize(12)).getServiceMessage());
    }
*/
}
