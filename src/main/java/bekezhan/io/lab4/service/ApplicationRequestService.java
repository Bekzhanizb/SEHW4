package bekezhan.io.lab4.service;

import bekezhan.io.lab4.entity.ApplicationRequest;
import bekezhan.io.lab4.repository.ApplicationRequestRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationRequestService {
    private final ApplicationRequestRepository repo;

    public ApplicationRequestService(ApplicationRequestRepository repo) {
        this.repo = repo;
    }

    public List<ApplicationRequest> getAllRequests() {
        return repo.findAll();
    }

    public List<ApplicationRequest> getRequestsByHandled(boolean handled) {
        return repo.findByHandled(handled);
    }

    public Optional<ApplicationRequest> getRequest(Long id) {
        return repo.findById(id);
    }

    public ApplicationRequest save(ApplicationRequest request) {
        return repo.save(request);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
