package hello.itemservice.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.itemservice.repository.ItemRepository;
import hello.itemservice.repository.jpa.JpaItemRepositoryV2;
import hello.itemservice.repository.jpa.JpaItemRepositoryV3;
import hello.itemservice.repository.jpa.SpringDataJpaItemRepository;
import hello.itemservice.repository.jpa.v2.ItemQueryRepository2;
import hello.itemservice.repository.jpa.v2.ItemRepositoryV2;
import hello.itemservice.service.ItemService;
import hello.itemservice.service.ItemServiceV1;
import hello.itemservice.service.ItemServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
@RequiredArgsConstructor
public class V2Config {
    private final SpringDataJpaItemRepository repository;

    private final EntityManager em;

    private final JPAQueryFactory query;

    @Bean
    public ItemService itemService() {
        return new ItemServiceV2((ItemRepositoryV2) itemRepository(), queryRepository2());
    }

    @Bean
    public ItemQueryRepository2 queryRepository2() {
        return new ItemQueryRepository2(query);
    }

    @Bean
    public ItemRepository itemRepository() {
        return new JpaItemRepositoryV3(em, query);
    }
}
