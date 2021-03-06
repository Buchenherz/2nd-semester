#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <string.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>

#define CHILDREN_AMOUNT 100
#define SHMSZ 27

int main(int argc, char *argv[]) {
	
	// For the shared_mem
	int *sharedInt;
	int shmid;
	key_t key = 5678;
	
	// For the FIFO
	FILE *fp_fifo;
//	int count;
//	char buf[4096];

	
	// create array of 100 process id's
	pid_t pids[CHILDREN_AMOUNT];
	int i;
	int n = CHILDREN_AMOUNT;
	
	if ((shmid = shmget(key, sizeof(int*), 0666)) < 0) {
		perror("shmget");
		exit(EXIT_FAILURE);
	}
		
	if ((sharedInt = (int*) shmat(shmid, NULL, 0)) == (int*) -1) {
		perror("shmat");
		exit(EXIT_FAILURE);
	}
	
	for (i = 0; i < n; i++) {
			// Create child process and check if errors occured
			pids[i] = fork();
			if (pids[i] < 0) {
				perror("fork");
				abort();
			} else if (pids[i] == 0) {
				printf("I am a child: %d with PID: %d\n",i, getpid());
				
				
				
				int* sharedIntStart = sharedInt;
				
				for (int i = 0; i < 100; i++){
					*sharedIntStart += 1;
				}
				
				// forces the child out of the cpu
				// Very, very important!
				printf("Child will exit now...\n");
				exit(0); 
			}
			wait(NULL); 
		}
		
		int* sharedIntStart = sharedInt;
		printf("%d\n", *sharedIntStart);
		 
		if ((fp_fifo = fopen("RESULT_FIFO", "w")) == NULL) {
			perror("fopen");
			exit(EXIT_FAILURE);
		}
		
		fprintf(fp_fifo, "%d", *sharedIntStart);
		
		if ( fclose(fp_fifo) == EOF ) {
			perror("fclose");
			exit(EXIT_FAILURE);
		}
		
		shmdt(sharedInt);
		shmctl(shmid, IPC_RMID, NULL);
		
	// wait for all processes to finish if something weird happens
	// wait removes the child process information from the kernel!
	wait(NULL);	
	return EXIT_SUCCESS;
}