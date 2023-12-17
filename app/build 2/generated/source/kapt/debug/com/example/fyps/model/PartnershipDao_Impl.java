package com.example.fyps.model;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
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
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PartnershipDao_Impl implements PartnershipDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<PartnershipEntity> __insertionAdapterOfPartnershipEntity;

  private final SharedSQLiteStatement __preparedStmtOfUpdateDocumentationLocalPath;

  private final SharedSQLiteStatement __preparedStmtOfUpdate;

  public PartnershipDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPartnershipEntity = new EntityInsertionAdapter<PartnershipEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `partnership` (`id`,`instiName`,`instiType`,`location`,`contactNum`,`reason`,`documentation`,`documentationName`,`documentationLocalPath`,`userId`,`status`) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, PartnershipEntity value) {
        if (value.getId() == null) {
          stmt.bindNull(1);
        } else {
          stmt.bindString(1, value.getId());
        }
        if (value.getInstiName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getInstiName());
        }
        if (value.getInstiType() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getInstiType());
        }
        if (value.getLocation() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getLocation());
        }
        if (value.getContactNum() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getContactNum());
        }
        if (value.getReason() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getReason());
        }
        if (value.getDocumentation() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getDocumentation());
        }
        if (value.getDocumentationName() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getDocumentationName());
        }
        if (value.getDocumentationLocalPath() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getDocumentationLocalPath());
        }
        if (value.getUserId() == null) {
          stmt.bindNull(10);
        } else {
          stmt.bindString(10, value.getUserId());
        }
        if (value.getStatus() == null) {
          stmt.bindNull(11);
        } else {
          stmt.bindString(11, value.getStatus());
        }
      }
    };
    this.__preparedStmtOfUpdateDocumentationLocalPath = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE partnership SET documentationLocalPath = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfUpdate = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "\n"
                + "    UPDATE partnership \n"
                + "    SET instiName = ?, instiType = ?, location = ?, \n"
                + "        contactNum = ?, reason = ?, documentation = ?,\n"
                + "        documentationName = ?, userId = ?, status = ?\n"
                + "    WHERE id = ?\n";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final PartnershipEntity partnership,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfPartnershipEntity.insert(partnership);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, continuation);
  }

  @Override
  public Object updateDocumentationLocalPath(final String id, final String filePath,
      final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateDocumentationLocalPath.acquire();
        int _argIndex = 1;
        if (filePath == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, filePath);
        }
        _argIndex = 2;
        if (id == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, id);
        }
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfUpdateDocumentationLocalPath.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object update(final String id, final String instiName, final String instiType,
      final String location, final String contactNum, final String reason,
      final String documentation, final String documentationName, final String userId,
      final String status, final Continuation<? super Unit> continuation) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfUpdate.acquire();
        int _argIndex = 1;
        if (instiName == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, instiName);
        }
        _argIndex = 2;
        if (instiType == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, instiType);
        }
        _argIndex = 3;
        if (location == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, location);
        }
        _argIndex = 4;
        if (contactNum == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, contactNum);
        }
        _argIndex = 5;
        if (reason == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, reason);
        }
        _argIndex = 6;
        if (documentation == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, documentation);
        }
        _argIndex = 7;
        if (documentationName == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, documentationName);
        }
        _argIndex = 8;
        if (userId == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, userId);
        }
        _argIndex = 9;
        if (status == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, status);
        }
        _argIndex = 10;
        if (id == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, id);
        }
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
          __preparedStmtOfUpdate.release(_stmt);
        }
      }
    }, continuation);
  }

  @Override
  public Object getPartnershipByUserId(final String userId,
      final Continuation<? super List<PartnershipEntity>> continuation) {
    final String _sql = "SELECT * FROM partnership WHERE userId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (userId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, userId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<PartnershipEntity>>() {
      @Override
      public List<PartnershipEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfInstiName = CursorUtil.getColumnIndexOrThrow(_cursor, "instiName");
          final int _cursorIndexOfInstiType = CursorUtil.getColumnIndexOrThrow(_cursor, "instiType");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfContactNum = CursorUtil.getColumnIndexOrThrow(_cursor, "contactNum");
          final int _cursorIndexOfReason = CursorUtil.getColumnIndexOrThrow(_cursor, "reason");
          final int _cursorIndexOfDocumentation = CursorUtil.getColumnIndexOrThrow(_cursor, "documentation");
          final int _cursorIndexOfDocumentationName = CursorUtil.getColumnIndexOrThrow(_cursor, "documentationName");
          final int _cursorIndexOfDocumentationLocalPath = CursorUtil.getColumnIndexOrThrow(_cursor, "documentationLocalPath");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final List<PartnershipEntity> _result = new ArrayList<PartnershipEntity>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final PartnershipEntity _item;
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpInstiName;
            if (_cursor.isNull(_cursorIndexOfInstiName)) {
              _tmpInstiName = null;
            } else {
              _tmpInstiName = _cursor.getString(_cursorIndexOfInstiName);
            }
            final String _tmpInstiType;
            if (_cursor.isNull(_cursorIndexOfInstiType)) {
              _tmpInstiType = null;
            } else {
              _tmpInstiType = _cursor.getString(_cursorIndexOfInstiType);
            }
            final String _tmpLocation;
            if (_cursor.isNull(_cursorIndexOfLocation)) {
              _tmpLocation = null;
            } else {
              _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            }
            final String _tmpContactNum;
            if (_cursor.isNull(_cursorIndexOfContactNum)) {
              _tmpContactNum = null;
            } else {
              _tmpContactNum = _cursor.getString(_cursorIndexOfContactNum);
            }
            final String _tmpReason;
            if (_cursor.isNull(_cursorIndexOfReason)) {
              _tmpReason = null;
            } else {
              _tmpReason = _cursor.getString(_cursorIndexOfReason);
            }
            final String _tmpDocumentation;
            if (_cursor.isNull(_cursorIndexOfDocumentation)) {
              _tmpDocumentation = null;
            } else {
              _tmpDocumentation = _cursor.getString(_cursorIndexOfDocumentation);
            }
            final String _tmpDocumentationName;
            if (_cursor.isNull(_cursorIndexOfDocumentationName)) {
              _tmpDocumentationName = null;
            } else {
              _tmpDocumentationName = _cursor.getString(_cursorIndexOfDocumentationName);
            }
            final String _tmpDocumentationLocalPath;
            if (_cursor.isNull(_cursorIndexOfDocumentationLocalPath)) {
              _tmpDocumentationLocalPath = null;
            } else {
              _tmpDocumentationLocalPath = _cursor.getString(_cursorIndexOfDocumentationLocalPath);
            }
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            _item = new PartnershipEntity(_tmpId,_tmpInstiName,_tmpInstiType,_tmpLocation,_tmpContactNum,_tmpReason,_tmpDocumentation,_tmpDocumentationName,_tmpDocumentationLocalPath,_tmpUserId,_tmpStatus);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  @Override
  public Object getDocumentationLocalPath(final String partnershipId,
      final Continuation<? super String> continuation) {
    final String _sql = "SELECT documentationLocalPath FROM partnership WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (partnershipId == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, partnershipId);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<String>() {
      @Override
      public String call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final String _result;
          if(_cursor.moveToFirst()) {
            if (_cursor.isNull(0)) {
              _result = null;
            } else {
              _result = _cursor.getString(0);
            }
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  @Override
  public Object getPartnershipById(final String id,
      final Continuation<? super PartnershipEntity> continuation) {
    final String _sql = "SELECT * FROM partnership WHERE id = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (id == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, id);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<PartnershipEntity>() {
      @Override
      public PartnershipEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfInstiName = CursorUtil.getColumnIndexOrThrow(_cursor, "instiName");
          final int _cursorIndexOfInstiType = CursorUtil.getColumnIndexOrThrow(_cursor, "instiType");
          final int _cursorIndexOfLocation = CursorUtil.getColumnIndexOrThrow(_cursor, "location");
          final int _cursorIndexOfContactNum = CursorUtil.getColumnIndexOrThrow(_cursor, "contactNum");
          final int _cursorIndexOfReason = CursorUtil.getColumnIndexOrThrow(_cursor, "reason");
          final int _cursorIndexOfDocumentation = CursorUtil.getColumnIndexOrThrow(_cursor, "documentation");
          final int _cursorIndexOfDocumentationName = CursorUtil.getColumnIndexOrThrow(_cursor, "documentationName");
          final int _cursorIndexOfDocumentationLocalPath = CursorUtil.getColumnIndexOrThrow(_cursor, "documentationLocalPath");
          final int _cursorIndexOfUserId = CursorUtil.getColumnIndexOrThrow(_cursor, "userId");
          final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
          final PartnershipEntity _result;
          if(_cursor.moveToFirst()) {
            final String _tmpId;
            if (_cursor.isNull(_cursorIndexOfId)) {
              _tmpId = null;
            } else {
              _tmpId = _cursor.getString(_cursorIndexOfId);
            }
            final String _tmpInstiName;
            if (_cursor.isNull(_cursorIndexOfInstiName)) {
              _tmpInstiName = null;
            } else {
              _tmpInstiName = _cursor.getString(_cursorIndexOfInstiName);
            }
            final String _tmpInstiType;
            if (_cursor.isNull(_cursorIndexOfInstiType)) {
              _tmpInstiType = null;
            } else {
              _tmpInstiType = _cursor.getString(_cursorIndexOfInstiType);
            }
            final String _tmpLocation;
            if (_cursor.isNull(_cursorIndexOfLocation)) {
              _tmpLocation = null;
            } else {
              _tmpLocation = _cursor.getString(_cursorIndexOfLocation);
            }
            final String _tmpContactNum;
            if (_cursor.isNull(_cursorIndexOfContactNum)) {
              _tmpContactNum = null;
            } else {
              _tmpContactNum = _cursor.getString(_cursorIndexOfContactNum);
            }
            final String _tmpReason;
            if (_cursor.isNull(_cursorIndexOfReason)) {
              _tmpReason = null;
            } else {
              _tmpReason = _cursor.getString(_cursorIndexOfReason);
            }
            final String _tmpDocumentation;
            if (_cursor.isNull(_cursorIndexOfDocumentation)) {
              _tmpDocumentation = null;
            } else {
              _tmpDocumentation = _cursor.getString(_cursorIndexOfDocumentation);
            }
            final String _tmpDocumentationName;
            if (_cursor.isNull(_cursorIndexOfDocumentationName)) {
              _tmpDocumentationName = null;
            } else {
              _tmpDocumentationName = _cursor.getString(_cursorIndexOfDocumentationName);
            }
            final String _tmpDocumentationLocalPath;
            if (_cursor.isNull(_cursorIndexOfDocumentationLocalPath)) {
              _tmpDocumentationLocalPath = null;
            } else {
              _tmpDocumentationLocalPath = _cursor.getString(_cursorIndexOfDocumentationLocalPath);
            }
            final String _tmpUserId;
            if (_cursor.isNull(_cursorIndexOfUserId)) {
              _tmpUserId = null;
            } else {
              _tmpUserId = _cursor.getString(_cursorIndexOfUserId);
            }
            final String _tmpStatus;
            if (_cursor.isNull(_cursorIndexOfStatus)) {
              _tmpStatus = null;
            } else {
              _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
            }
            _result = new PartnershipEntity(_tmpId,_tmpInstiName,_tmpInstiType,_tmpLocation,_tmpContactNum,_tmpReason,_tmpDocumentation,_tmpDocumentationName,_tmpDocumentationLocalPath,_tmpUserId,_tmpStatus);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, continuation);
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
