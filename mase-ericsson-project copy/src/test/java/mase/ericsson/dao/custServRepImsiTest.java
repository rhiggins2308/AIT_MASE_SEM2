package mase.ericsson.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import mase.ericsson.dao.NetworkEventsDAO;
import mase.ericsson.dao.TP21_IMSI_Failures_DAO;
import mase.ericsson.entities.BaseData;
import mase.ericsson.test.setup.BaseDataFactory;

public class custServRepImsiTest {

    private static final String TEST_DB_INSTANCE = "test-db";

    @Mock
    private EntityManager mockedEntityManager;
    @Mock
    private Query mockedQuery;

    @InjectMocks
    private TP21_IMSI_Failures_DAO tP21_IMSI_Failures_DAO;

    @BeforeEach
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        tP21_IMSI_Failures_DAO.setEntityManager(mockedEntityManager);

        // Old init
        tP21_IMSI_Failures_DAO = new TP21_IMSI_Failures_DAO();
        tP21_IMSI_Failures_DAO.em = mockedEntityManager;
    }

    @AfterEach
    public void closeEntityManager() {
        mockedEntityManager.close();
    }

//    @Test
//    public void testCustSerRep_Positive() {
//        // given
//        long imsi = 123L;
//        Date fromDate = new Date(2020, 2, 17);
//        Date toDate = new Date(2021, 2, 17);
//        // Expected result
//        List<BaseData> expectedBaseData = new ArrayList<>();
//        expectedBaseData.add(BaseDataFactory.createSampleBaseData());
//        // Mock the return value
//        Mockito.when(mockedEntityManager.createQuery(Matchers.anyString())).thenReturn(mockedQuery);
//        Mockito.when(mockedQuery.getResultList()).thenReturn(expectedBaseData);
//
//        // when
//        List<Object[]> resultBaseData = tP21_IMSI_Failures_DAO.imsiCountForServiceRep(imsi, fromDate, toDate);
//
//        // then
//        assertThat(resultBaseData, is(expectedBaseData));
//        // assertThat(resultBaseData.size(), is(0));
//    }

    @Test
    public void testCustSerRep_Negative() {
        // given
        long imsi = 123L;
        Date fromDate = new Date(2020, 2, 17);
        Date toDate = new Date(2021, 2, 17);
        // Mock the return value
        Mockito.when(mockedEntityManager.createQuery(Matchers.anyString())).thenThrow(IllegalStateException.class);

        // when
        List<Object[]> resultBaseData = tP21_IMSI_Failures_DAO.imsiCountForServiceRep(imsi, fromDate, toDate);

        // then
        assertThat(resultBaseData.size(), is(0));
    }

}
