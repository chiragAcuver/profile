package com.pgb.profile.data.couchbase;

import com.pgb.profile.data.Profile;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.ReactiveCouchbaseRepository;
import org.springframework.data.couchbase.repository.ReactiveCouchbaseSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@N1qlPrimaryIndexed
@ViewIndexed(designDoc="profile",viewName="all")
public interface ReactiveProfileRepository  extends ReactiveCouchbaseSortingRepository<Profile,String> {
}
