package edu.miu.cs544.backend.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import edu.miu.cs544.backend.domain.AcademicBlock;
import edu.miu.cs544.backend.exceptions.DatabaseException;
import edu.miu.cs544.backend.repositories.AcademicBlockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Ignore;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {AcademicBlockServiceImpl.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class AcademicBlockServiceImplTest {
    @MockBean
    private AcademicBlockRepository academicBlockRepository;

    @Autowired
    private AcademicBlockServiceImpl academicBlockServiceImpl;

    /**
     * Method under test: {@link AcademicBlockServiceImpl#findAll()}
     */
    @Test
    public void testFindAll() {
        ArrayList<AcademicBlock> academicBlockList = new ArrayList<>();
        when(academicBlockRepository.findAll()).thenReturn(academicBlockList);
        List<AcademicBlock> actualFindAllResult = academicBlockServiceImpl.findAll();
        assertSame(academicBlockList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(academicBlockRepository).findAll();
    }

    /**
     * Method under test: {@link AcademicBlockServiceImpl#findById(Long)}
     */
    @Test
    public void testFindById() {
        AcademicBlock academicBlock = new AcademicBlock();
        when(academicBlockRepository.findById((Long) any())).thenReturn(Optional.of(academicBlock));
        assertSame(academicBlock, academicBlockServiceImpl.findById(123L));
        verify(academicBlockRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link AcademicBlockServiceImpl#findById(Long)}
     */
    @Test
    @Ignore("TODO: Complete this test")
    public void testFindById2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.util.NoSuchElementException: No value present
        //       at java.util.Optional.get(Optional.java:143)
        //       at edu.miu.cs544.backend.service.AcademicBlockServiceImpl.findById(AcademicBlockServiceImpl.java:23)
        //   See https://diff.blue/R013 to resolve this issue.

        when(academicBlockRepository.findById((Long) any())).thenReturn(Optional.empty());
        academicBlockServiceImpl.findById(123L);
    }

    /**
     * Method under test: {@link AcademicBlockServiceImpl#delete(Long)}
     */
    @Test
    public void testDelete() {
        doNothing().when(academicBlockRepository).deleteById((Long) any());
        academicBlockServiceImpl.delete(123L);
        verify(academicBlockRepository).deleteById((Long) any());
        assertTrue(academicBlockServiceImpl.findAll().isEmpty());
    }

    /**
     * Method under test: {@link AcademicBlockServiceImpl#create(AcademicBlock)}
     */
    @Test
    public void testCreate() throws DatabaseException {
        AcademicBlock academicBlock = new AcademicBlock();
        when(academicBlockRepository.save((AcademicBlock) any())).thenReturn(academicBlock);
        assertSame(academicBlock, academicBlockServiceImpl.create(new AcademicBlock()));
        verify(academicBlockRepository).save((AcademicBlock) any());
    }

    /**
     * Method under test: {@link AcademicBlockServiceImpl#update(Long, AcademicBlock)}
     */
    @Test
    public void testUpdate() {
        assertFalse(academicBlockServiceImpl.update(123L, new AcademicBlock()));
        assertFalse(academicBlockServiceImpl.update(123L, mock(AcademicBlock.class)));
    }
}

