package jdbi;

import com.github.jaketaylorpro.dropwizardgradle.api.Record;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;


public interface RecordDAO {
    @SqlUpdate("create table something (id int primary key, name varchar(100))")
    void createRecordTable();

    @SqlUpdate("insert into something (id, name) values (:id, :name)")
    void insert(@Bind("id") int id, @Bind("name") String name);

    @SqlQuery("select * from something where id = :id")
    Record findById(@Bind("id") int id);
}