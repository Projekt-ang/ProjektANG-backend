package pl.ang.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.ang.backend.model.Result;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    @Query(value = "SELECT * FROM result r WHERE r.user_id = :userId and r.reading_video_test_id = :testId",
            nativeQuery = true)
    Result findResultByReadingVideoTestIdAndUserId(
            @Param("userId") Long userId,
            @Param("testId") Long testId);
    @Query(value = "SELECT * FROM result r WHERE r.user_id = :userId and r.blank_insert_test_id = :testId",
            nativeQuery = true)
    Result findResultByBlankInsertTestIdAndUserId(
            @Param("userId") Long userId,
            @Param("testId") Long testId);

    @Query(value = "SELECT * FROM result r WHERE r.user_id = :userId",
            nativeQuery = true)
    List<Result> findResultByUserId(
            @Param("userId") Long userId);
}
