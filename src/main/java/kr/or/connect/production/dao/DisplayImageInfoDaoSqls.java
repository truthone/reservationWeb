package kr.or.connect.production.dao;

public class DisplayImageInfoDaoSqls {
  public static final String SELECT_DISPLAYINFOIMAGE_INFO =
    "SELECT file_info.content_type AS contentType, \r\n" +
    "file_info.create_date AS createDate,\r\n" +
    "file_info.delete_flag AS deleteFlag, \r\n" +
    "display_info_image.id AS displayInfoId, \r\n" +
    "display_info_image.file_id AS fileId,\r\n" +
    "file_info.file_name AS fileName,\r\n" +
    "file_info.modify_date AS modifyDate,\r\n" +
    "file_info.save_file_name AS saveFileName\r\n" +
    "FROM file_info, display_info_image\r\n" +
    "WHERE display_info_image.display_info_id = :displayInfoId\r\n" +
    "AND display_info_image.file_id = file_info.id";
}
