package jdbi;

import com.github.jaketaylorpro.dropwizardgradle.api.Record;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecordMapper implements ResultSetMapper<Record> {
    @Override
    public Record map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        return new Record(r.getLong("id"),r.getString("name"),r.getDate("insertDate"));
    }
}
