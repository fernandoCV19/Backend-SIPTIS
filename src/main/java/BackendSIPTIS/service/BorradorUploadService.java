package BackendSIPTIS.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import com.dropbox.core.DbxException;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.UploadBuilder;
import com.dropbox.core.v2.files.WriteMode;

public class BorradorUploadService {
    private static final String ACCESS_TOKEN = "sl.BaIPpbHhUkEnC7FpE6_M6qoMp2dUHP0jw5IbDN1zse6vMsoPml14WzehfBCwxxMe2bSGAJIIz6tWXsL2R9rJQXlNROvsbJpIYs43dSFspUL4016ddt31Y7ffQ5p2dfXmVUtt074p";

    public static void main(String[] args) throws DbxException, IOException {
        // Create Dropbox client
        DbxRequestConfig config = DbxRequestConfig.newBuilder("dropbox/Siptis-Cloud").build();
        DbxClientV2 client = new DbxClientV2(config, ACCESS_TOKEN);

        System.out.println(client.users().getCurrentAccount().getEmail());

        File archivo = new File("D:\\Descargas\\HORA_PABLO-2-2022.xlsx");
        InputStream inputStream = new FileInputStream(archivo);

        UploadBuilder uploadBuilder = client.files().uploadBuilder("/Borradores/"+ archivo.getName());
        uploadBuilder.withClientModified(new Date(archivo.lastModified()));
        uploadBuilder.withMode(WriteMode.ADD);
        uploadBuilder.withAutorename(true);

        uploadBuilder.uploadAndFinish(inputStream);
        inputStream.close();
    }
}
