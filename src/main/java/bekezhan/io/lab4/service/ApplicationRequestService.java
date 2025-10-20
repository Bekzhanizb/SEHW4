package bekezhan.io.lab4.service;

;
import bekezhan.io.lab4.entity.ApplicationRequest;
import bekezhan.io.lab4.repository.ApplicationRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationRequestService {

    @Autowired
    private ApplicationRequestRepository repository;

    public List<ApplicationRequest> findAll() {
        return repository.findAll();
    }

    public List<ApplicationRequest> findByHandled(boolean handled) {
        return repository.findByHandled(handled);
    }

    public Optional<ApplicationRequest> findById(Long id) {
        return repository.findById(id);
    }

    public ApplicationRequest save(ApplicationRequest request) {
        return repository.save(request);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public ApplicationRequest processRequest(Long id) {
        Optional<ApplicationRequest> request = repository.findById(id);
        if (request.isPresent()) {
            ApplicationRequest applicationRequest = request.get();
            applicationRequest.setHandled(true);
            return repository.save(applicationRequest);
        }
        return null;
    }
}