void read_server_config(){
 std::string config = "";
 
 char *filename = "server.conf";
 std::ifstream file(filename);
 
 std::string wfile_content;
 while(std::getline(file, wfile_content)){
  if(wfile_content[0]!='#' && wfile_content[1]!='#'){
    config = config + wfile_content + "\n";
  }
 }
 
 std::string qry = "INSERT INTO Server_config (Descript) VALUES (\'"+config+"\');";
 
 if(mysql_query(connect,qry.c_str())){
    std::cout<<mysql_errno(connect)<<std::endl;
 }
}

