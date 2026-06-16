package ru.hogwarts.school.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.repository.AvatarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AvatarService {
    private static final Logger logger = LoggerFactory.getLogger(AvatarService.class);
    public final AvatarRepository avatarRepository;
    public AvatarService(AvatarRepository avatarRepository) {
        this.avatarRepository = avatarRepository;
    }
    public Page<Avatar> getAvatar(int page, int size) {
        logger.info("getAvatar");
        logger.debug("Page = {},Size = {}", page, size);
        return avatarRepository.findAll(PageRequest.of(page, size));
    }
}
