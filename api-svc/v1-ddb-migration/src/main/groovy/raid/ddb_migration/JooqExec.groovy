package raid.ddb_migration

import org.jooq.Configuration
import org.jooq.DSLContext
import org.jooq.SQLDialect
import org.jooq.TransactionalRunnable
import org.jooq.conf.RenderQuotedNames
import org.jooq.impl.DSL
import org.jooq.impl.DefaultConnectionProvider

import java.sql.Connection
import java.sql.DriverManager
import java.util.function.BiFunction
import java.util.function.Function

import static org.jooq.conf.RenderQuotedNames.EXPLICIT_DEFAULT_UNQUOTED
import static raid.ddb_migration.Util.printExecTime

class JooqExec {
  // these have to match the ext props from the gradle script to work
  String url = System.properties['raidV1PgUrl']
  String user = System.properties['raidV1PgUser']
  String password = System.properties['raidV1PgPassword']

  JooqExec() {
//    println "db url:" + System.properties['raidV1PgUrl']
  }
  
  static DSLContext createDsl(Connection conn){
    // will use the DefaultConnectionProvider
    DSLContext db = DSL.using(conn, SQLDialect.POSTGRES);
    db.configuration().settings().
      setRenderQuotedNames(EXPLICIT_DEFAULT_UNQUOTED)
    return db
  }
  
  DSLContext createDsl(){
    Connection conn = DriverManager.getConnection(url, user, password);
    return createDsl(conn)
  }
  
  public <TResult> TResult withDb(Function<DSLContext, TResult> action) {
    DSLContext db = createDsl()
    try {
      TResult result
      db.transaction(new TransactionalRunnable() {
        @Override
        void run(Configuration config) throws Throwable {
          result = action.apply(config.dsl())
        }
      })
      return result
    }
    catch( e ){
      throw e
    }
    finally {
      // dodgy, but it'll work for the moment
      ((DefaultConnectionProvider) db.configuration().connectionProvider()).
        acquire().close()
    }
  }

  public <TExportLine extends DdbS3ExportLine> void mergeItems(
    String description,
    List<TExportLine> items, 
    BiFunction<DSLContext, TExportLine, Record> mapRecord
  ) {
    printExecTime("$description items=${items.size()}" , () -> {
      withDb(db -> {
        items.each{ iItem -> {
            mapRecord(db, iItem).merge()
          }
        }
      })
    })
  }


  /**
   * IMPROVE:STO better name, the uppercase W is to differentiate from the 
   * instance method.
   */
  static <TResult> TResult WithDb(Function<DSLContext, TResult> action) {
    new JooqExec().withDb(action)
  }
}