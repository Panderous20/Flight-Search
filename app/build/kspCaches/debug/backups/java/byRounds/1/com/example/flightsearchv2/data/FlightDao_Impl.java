package com.example.flightsearchv2.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.flightsearchv2.model.Airport;
import com.example.flightsearchv2.model.Favorite;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class FlightDao_Impl implements FlightDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Favorite> __insertionAdapterOfFavorite;

  private final EntityDeletionOrUpdateAdapter<Favorite> __deletionAdapterOfFavorite;

  public FlightDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFavorite = new EntityInsertionAdapter<Favorite>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `favorite` (`id`,`departure_code`,`destination_code`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Favorite entity) {
        statement.bindLong(1, entity.getId());
        statement.bindString(2, entity.getDepartureCode());
        statement.bindString(3, entity.getDestinationCode());
      }
    };
    this.__deletionAdapterOfFavorite = new EntityDeletionOrUpdateAdapter<Favorite>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `favorite` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Favorite entity) {
        statement.bindLong(1, entity.getId());
      }
    };
  }

  @Override
  public Object insertFavorite(final Favorite favorite,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFavorite.insert(favorite);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteFavorite(final Favorite favorite,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfFavorite.handle(favorite);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllAirport(final Continuation<? super List<Airport>> $completion) {
    final String _sql = "SELECT * FROM airport ORDER BY name ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Airport>>() {
      @Override
      @NonNull
      public List<Airport> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCode = CursorUtil.getColumnIndexOrThrow(_cursor, "iata_code");
          final int _cursorIndexOfPassengers = CursorUtil.getColumnIndexOrThrow(_cursor, "passengers");
          final List<Airport> _result = new ArrayList<Airport>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Airport _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpCode;
            _tmpCode = _cursor.getString(_cursorIndexOfCode);
            final int _tmpPassengers;
            _tmpPassengers = _cursor.getInt(_cursorIndexOfPassengers);
            _item = new Airport(_tmpId,_tmpName,_tmpCode,_tmpPassengers);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Airport>> getAllAirportByFlow() {
    final String _sql = "\n"
            + "        SELECT * FROM airport\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"airport"}, new Callable<List<Airport>>() {
      @Override
      @NonNull
      public List<Airport> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCode = CursorUtil.getColumnIndexOrThrow(_cursor, "iata_code");
          final int _cursorIndexOfPassengers = CursorUtil.getColumnIndexOrThrow(_cursor, "passengers");
          final List<Airport> _result = new ArrayList<Airport>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Airport _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpCode;
            _tmpCode = _cursor.getString(_cursorIndexOfCode);
            final int _tmpPassengers;
            _tmpPassengers = _cursor.getInt(_cursorIndexOfPassengers);
            _item = new Airport(_tmpId,_tmpName,_tmpCode,_tmpPassengers);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Flow<List<Airport>> getAllAirportByQuery(final String query) {
    final String _sql = "\n"
            + "        SELECT * FROM airport\n"
            + "        WHERE iata_code LIKE '%' || ? || '%'\n"
            + "        OR name LIKE '%' || ? || '%' \n"
            + "        ORDER BY passengers DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, query);
    _argIndex = 2;
    _statement.bindString(_argIndex, query);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"airport"}, new Callable<List<Airport>>() {
      @Override
      @NonNull
      public List<Airport> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCode = CursorUtil.getColumnIndexOrThrow(_cursor, "iata_code");
          final int _cursorIndexOfPassengers = CursorUtil.getColumnIndexOrThrow(_cursor, "passengers");
          final List<Airport> _result = new ArrayList<Airport>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Airport _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpCode;
            _tmpCode = _cursor.getString(_cursorIndexOfCode);
            final int _tmpPassengers;
            _tmpPassengers = _cursor.getInt(_cursorIndexOfPassengers);
            _item = new Airport(_tmpId,_tmpName,_tmpCode,_tmpPassengers);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getAirportByCode(final String code,
      final Continuation<? super Airport> $completion) {
    final String _sql = "\n"
            + "        SELECT *  FROM airport \n"
            + "        WHERE iata_code = ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, code);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Airport>() {
      @Override
      @NonNull
      public Airport call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCode = CursorUtil.getColumnIndexOrThrow(_cursor, "iata_code");
          final int _cursorIndexOfPassengers = CursorUtil.getColumnIndexOrThrow(_cursor, "passengers");
          final Airport _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpCode;
            _tmpCode = _cursor.getString(_cursorIndexOfCode);
            final int _tmpPassengers;
            _tmpPassengers = _cursor.getInt(_cursorIndexOfPassengers);
            _result = new Airport(_tmpId,_tmpName,_tmpCode,_tmpPassengers);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getAirportListByCode(final String code,
      final Continuation<? super List<Airport>> $completion) {
    final String _sql = "\n"
            + "        SELECT * FROM airport\n"
            + "        WHERE NOT iata_code = ?\n"
            + "        ORDER BY passengers DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, code);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Airport>>() {
      @Override
      @NonNull
      public List<Airport> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
          final int _cursorIndexOfCode = CursorUtil.getColumnIndexOrThrow(_cursor, "iata_code");
          final int _cursorIndexOfPassengers = CursorUtil.getColumnIndexOrThrow(_cursor, "passengers");
          final List<Airport> _result = new ArrayList<Airport>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Airport _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpName;
            _tmpName = _cursor.getString(_cursorIndexOfName);
            final String _tmpCode;
            _tmpCode = _cursor.getString(_cursorIndexOfCode);
            final int _tmpPassengers;
            _tmpPassengers = _cursor.getInt(_cursorIndexOfPassengers);
            _item = new Airport(_tmpId,_tmpName,_tmpCode,_tmpPassengers);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<Favorite>> getALLFavoriteByFlow() {
    final String _sql = "\n"
            + "        SELECT * FROM favorite\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"favorite"}, new Callable<List<Favorite>>() {
      @Override
      @NonNull
      public List<Favorite> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDepartureCode = CursorUtil.getColumnIndexOrThrow(_cursor, "departure_code");
          final int _cursorIndexOfDestinationCode = CursorUtil.getColumnIndexOrThrow(_cursor, "destination_code");
          final List<Favorite> _result = new ArrayList<Favorite>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Favorite _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpDepartureCode;
            _tmpDepartureCode = _cursor.getString(_cursorIndexOfDepartureCode);
            final String _tmpDestinationCode;
            _tmpDestinationCode = _cursor.getString(_cursorIndexOfDestinationCode);
            _item = new Favorite(_tmpId,_tmpDepartureCode,_tmpDestinationCode);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getALLFavorite(final Continuation<? super List<Favorite>> $completion) {
    final String _sql = "\n"
            + "        SELECT * FROM favorite\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Favorite>>() {
      @Override
      @NonNull
      public List<Favorite> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDepartureCode = CursorUtil.getColumnIndexOrThrow(_cursor, "departure_code");
          final int _cursorIndexOfDestinationCode = CursorUtil.getColumnIndexOrThrow(_cursor, "destination_code");
          final List<Favorite> _result = new ArrayList<Favorite>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Favorite _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpDepartureCode;
            _tmpDepartureCode = _cursor.getString(_cursorIndexOfDepartureCode);
            final String _tmpDestinationCode;
            _tmpDestinationCode = _cursor.getString(_cursorIndexOfDestinationCode);
            _item = new Favorite(_tmpId,_tmpDepartureCode,_tmpDestinationCode);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getSingleFavorite(final String departureCode, final String destinationCode,
      final Continuation<? super Favorite> $completion) {
    final String _sql = "\n"
            + "        SELECT * FROM favorite\n"
            + "        WHERE departure_code = ? AND destination_code = ?\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindString(_argIndex, departureCode);
    _argIndex = 2;
    _statement.bindString(_argIndex, destinationCode);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Favorite>() {
      @Override
      @Nullable
      public Favorite call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDepartureCode = CursorUtil.getColumnIndexOrThrow(_cursor, "departure_code");
          final int _cursorIndexOfDestinationCode = CursorUtil.getColumnIndexOrThrow(_cursor, "destination_code");
          final Favorite _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpDepartureCode;
            _tmpDepartureCode = _cursor.getString(_cursorIndexOfDepartureCode);
            final String _tmpDestinationCode;
            _tmpDestinationCode = _cursor.getString(_cursorIndexOfDestinationCode);
            _result = new Favorite(_tmpId,_tmpDepartureCode,_tmpDestinationCode);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
