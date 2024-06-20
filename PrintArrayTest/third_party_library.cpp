#include <iostream>

extern "C" {
    unsigned int printArray(unsigned int ip, unsigned short port, unsigned char *buffer, unsigned int length) {
        if (buffer == nullptr || length <= 0) {
            return false;
        }
        
        for (unsigned int i = 0; i < length; ++i) {
            std::cout << buffer[i];
        }
        std::cout << std::endl;
        
        return 999;
    }
}