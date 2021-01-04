package com.responsywnie.tasks.logic;

import com.responsywnie.tasks.config.TasksConfigurationProperties;
import com.responsywnie.tasks.repositories.TaskGroupRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProjectServiceTest {

    @Test
    @DisplayName("should throw IllegalStateException when configured to allow just 1 group and other undone")
    void createGroup_noMultipleGroupsConfig_And_openGroups_throwsIllegalStateException() {
        //given
        var mockGroupRepository = mock(TaskGroupRepository.class);
        when(mockGroupRepository.existsByDoneIsFalseAndProject_Id(anyInt())).thenReturn(true);
        assertTrue(mockGroupRepository.existsByDoneIsFalseAndProject_Id(1));
        //and
        var mockTemplate = mock(TasksConfigurationProperties.Template.class);
        when(mockTemplate.isAllowMultipleTasks()).thenReturn(false);
        //and
        var mockConfig = mock(TasksConfigurationProperties.class);
        when(mockConfig.getTemplate()).thenReturn(mockTemplate);
        //system under test
        var toTest = new ProjectService(null,mockGroupRepository,mockConfig);
        //when

        //then

    }
}
