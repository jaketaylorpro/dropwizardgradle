package jdbi;

import com.github.jaketaylorpro.dropwizardgradle.api.Record;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(RecordMapper.class)
public interface RecordDAO {
    @SqlUpdate("insert into test1 (name) values (:name)")
    void insert(@Bind("name") String name);

    @SqlQuery("select * from test1 where id = :id")
    Record findById(@Bind("id") int id);
}