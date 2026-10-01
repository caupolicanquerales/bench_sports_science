package com.capo.bench_sports_science.components;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.batch.core.job.JobExecution;
import org.springframework.batch.core.job.parameters.JobParametersBuilder;
import org.springframework.context.ApplicationEventPublisher;

import com.capo.bench_sports_science.models.FileRegisterModel;
import com.capo.bench_sports_science.repository.FileRegisterRepository;

@ExtendWith(MockitoExtension.class)
class FileRegisterJobListenerTest {

    @Mock
    private FileRegisterRepository fileRegisterRepository;

    @Mock
    private ApplicationEventPublisher eventPublisher;

    @Captor
    private ArgumentCaptor<FileRegisterModel> fileRegisterCaptor;

    private FileRegisterJobListener listener;

    @BeforeEach
    void setUp() {
        listener = new FileRegisterJobListener(fileRegisterRepository, eventPublisher);
    }

    @Test
    void beforeJob_shouldPersistUserIdFromJobParameters() {
        var params = new JobParametersBuilder()
                .addString("fileName", "activity.csv")
                .addString("userId", "42")
                .toJobParameters();

        JobExecution jobExecution = mock(JobExecution.class);
        var executionContext = new org.springframework.batch.infrastructure.item.ExecutionContext();

        when(jobExecution.getJobParameters()).thenReturn(params);
        when(jobExecution.getExecutionContext()).thenReturn(executionContext);

        when(fileRegisterRepository.save(any(FileRegisterModel.class))).thenAnswer(invocation -> {
            FileRegisterModel model = invocation.getArgument(0);
            model.setId(10L);
            return model;
        });

        listener.beforeJob(jobExecution);

        verify(fileRegisterRepository).save(fileRegisterCaptor.capture());
        assertEquals("42", fileRegisterCaptor.getValue().getUserId());
        assertEquals(10L, jobExecution.getExecutionContext().getLong("fileRegisterId"));
    }
}
