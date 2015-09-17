#####--INFO --- To run it update path and copy following header command into mysql command prompt.
#########---If you need users and their relations then take those two out and run one by one.
##How to create a new user and grant permision after login with root user
##CREATE USER 'ADM_PRE'@'%' IDENTIFIED BY 'ADM_PRE';
##GRANT ALL PRIVILEGES ON ADM_PRE.* To 'ADM_PRE'@'%';
##FLUSH PRIVILEGES;

#########\. /home/edassri/workspace/IT_MSSP/DB/initData/DMLs/init.sql             

\. C:\\Users\\esonray\\Documents\\Projects\\ADM_PRE\\DB\\initData\\DMLs\\UserApplicationRole_DMLs.sql



\. C:\\Users\\esonray\\Documents\\Projects\\ADM_PRE\\DB\\initData\\DMLs\\ProductDetails_DMLs.sql

\. C:\\Users\\esonray\\Documents\\Projects\\ADM_PRE\\DB\\initData\\DMLs\\Component_DMLs.sql

\. C:\\Users\\esonray\\Documents\\Projects\\ADM_PRE\\DB\\initData\\DMLs\\ProductEstimationActivities_DMLs.sql

\. C:\\Users\\esonray\\Documents\\Projects\\ADM_PRE\\DB\\initData\\DMLs\\EstimationComplexityLookup_DMLs.sql

\. C:\\Users\\esonray\\Documents\\Projects\\ADM_PRE\\DB\\initData\\DMLs\\ProductActivitiesComplexityAssumptions_DMLs.sql
