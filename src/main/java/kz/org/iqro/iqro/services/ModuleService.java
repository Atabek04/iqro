package kz.org.iqro.iqro.services;

import kz.org.iqro.iqro.entities.Module;
import kz.org.iqro.iqro.repositories.ModuleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModuleService {
    private final ModuleRepository moduleRepository;

    public ModuleService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

    public List<Module> getAllModulesByCourseId(Long courseId) {
        return moduleRepository.findAllByCourseId(courseId);
    }

    public void saveModule(Module module) {
        moduleRepository.save(module);
    }

    public Module getModuleById(int moduleId) {
        return moduleRepository.findById(moduleId).orElse(null);
    }

    public void deleteModule(int moduleId) {
        moduleRepository.deleteById(moduleId);
    }
}
