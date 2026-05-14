package ru.hogwarts.school.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.repository.AvatarRepository;
import org.springframework.data.domain.Pageable;

@Service
public class AvatarService {
    public final AvatarRepository avatarRepository;
    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }
    public Page<Avatar> getAvatar(int page, int size) {
        return avatarRepository.findAll(PageRequest.of(page, size));
    }
}
