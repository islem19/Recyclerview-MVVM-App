package dz.islem.mvvmarch.ui.main;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import dz.islem.mvvmarch.data.db.dao.MessageDAO;
import dz.islem.mvvmarch.data.network.model.Message;
import dz.islem.mvvmarch.data.network.services.RemoteService;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class MainViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    @Mock
    MessageDAO messageDAO;
    @Mock
    RemoteService remoteService;
    private MainViewModel viewModel;
    @Mock
    Observer<List<Message>> messageObserver;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        viewModel = new MainViewModel(messageDAO,remoteService);
        viewModel.getSavedMessages().observeForever(messageObserver);
    }

    @Test
    public void testNull(){
        assertNotNull(viewModel.getSavedMessages());
        assertTrue(viewModel.getSavedMessages().hasObservers());
    }

    @Test
    public void testEmptyDb(){
        //given
        when(messageDAO.getAll()).thenReturn(Collections.emptyList());
        // act
        viewModel.loadDb();
        // verify
        assertEquals(messageDAO.getAll(),Collections.emptyList());
        then(messageObserver).should(times(0)).onChanged(null);
    }

    @Test
    public void testinsertElementInDb(){

    }


}