void execute_events(){
  for(int e = 0; e < 10; e++){
   //if(strlen(sysreqs[e].c_str())>0){
    //std::string debs = "<BrickVox SERVER>> Request: " + sysreqs[e];
    //bon_push_into_cmd(debs.c_str());
    char temporal[128];     
    char event[128];
    char parameter[128];
    strcpy(temporal, sysreqs[e].c_str()); 
    sscanf(temporal, "%s %s", &event, &parameter);
    // ------------------KICKED--------------------- 
     if(strcmp(event, "!kick") == 0){
       if(strcmp(parameter, tmp_username.c_str()) == 0){
         exit(1);
       }
     }
    // ------------------BANNED---------------------
    if(strcmp(event, "!banned") == 0){
       if(strcmp(parameter, tmp_username.c_str()) == 0){
         exit(1);
       }
     }
   }
  //}
}
