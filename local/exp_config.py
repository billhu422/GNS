import os, sys

# SET
experiment_run_time = -1

# SET
scheme = 'locality'
schemes = {'beehive':0, 'locality':1, 'uniform':2, 'static3':3, 'replicate_all':4}

# SET
lookupTrace = 'lookupTrace10'
updateTrace = 'updateTrace10'

#set
regular_workload = 0
mobile_workload = 0

# SET: not used
num_ns = 80
num_lns = 80


queryTimeout = 3000
maxQueryWaitTime = 9100



ns_sleep = 2

failed_nodes = None




load_balancing = False

primary_name_server = 3

replication_interval = 3

is_beehive_replication = False
is_location_replication = False
is_random_replication = False
is_static_replication = False


nslog = 'FINE'
nslogstat = 'FINE'
lnslog = 'FINE'
lnslogstat = 'FINE'


gnrs_dir = '/Users/abhigyan/Documents/workspace/GNS2/'

paxos_log_folder = '/Users/abhigyan/Documents/workspace/GNS2/local/paxoslog/'
delete_paxos_log = True

if scheme not in schemes:
    print 'ERROR: Scheme name not valid:', scheme, 'Valid scheme names:', schemes.keys()
    sys.exit(2)

if scheme == 'beehive':
    is_beehive_replication = True
    load_balancing = False
elif scheme == 'locality':
    is_location_replication = True
elif scheme == 'uniform':
    is_random_replication = True
elif scheme == 'static3':
    is_static_replication = True
elif scheme == 'replicate_all':
    is_static_replication = True
    primary_name_server = num_ns
