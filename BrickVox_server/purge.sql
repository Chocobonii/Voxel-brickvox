DELETE FROM Players_ingame WHERE Player_ID > 0;
ALTER TABLE Players_ingame AUTO_INCREMENT = 1;

DELETE FROM Modifications WHERE Chunk_ID > 0; 
ALTER TABLE Modifications AUTO_INCREMENT = 1;

DELETE FROM chat_data WHERE Message_ID > 0; 
ALTER TABLE chat_data AUTO_INCREMENT = 1;

DELETE FROM custom_object WHERE Obj_ID > 0; 
ALTER TABLE custom_object AUTO_INCREMENT = 1;

ALTER TABLE System_request AUTO_INCREMENT = 1;

DELETE FROM Server_config WHERE Config_Id > 0; 
ALTER TABLE Server_config AUTO_INCREMENT = 1;
