package com.responsywnie.tasks.logic;

import com.responsywnie.tasks.model.Task;
import com.responsywnie.tasks.repositories.TaskGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TempService {

    List<String> temp(TaskGroupRepository repository){
        //FIXME: N + 1
       return repository.findAll().stream()//pobieramy wszystkie taski z grupy
                .flatMap(taskGroup -> taskGroup.getTasks().stream())
                .map(Task::getDescription)
               .collect(Collectors.toList());
    }
}
