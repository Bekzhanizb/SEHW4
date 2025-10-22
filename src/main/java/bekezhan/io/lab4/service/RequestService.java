package bekezhan.io.lab4.service;

;
import bekezhan.io.lab4.entity.Request;
import bekezhan.io.lab4.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    @Autowired
    private RequestRepository repo;

    public List<Request> findAll() {
        return repo.findAll();
    }

    public List<Request> findByHandled(boolean handled) {
        return repo.findByHandled(handled);
    }

    public Optional<Request> findById(Long id) {
        return repo.findById(id);
    }

    public Request save(Request request) {
        return repo.save(request);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public void processRequest(Long id) {
        Optional<Request> request = repo.findById(id);
        if (request.isPresent()) {
            Request applicationRequest = request.get();
            applicationRequest.setHandled(true);
            repo.save(applicationRequest);
        }
    }
}