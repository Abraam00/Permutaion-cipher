#include <iostream>
#include <unistd.h>
#include <fstream>
#include <string>
using namespace std;

void Enc(ifstream &inFile, ofstream &outFile, int size, int *perm)
{
    char c;
    // needed to check if the text is not evenly distrubuted by the size (padding letter is *)
    int counter = 0;
    // temp is populated by the text file size chunks at a time
    char *temp = new char[size];
    // cipher has the same letters as temp after applying the permutation
    char *cipher = new char[size];
    if (inFile.is_open())
    {
        while (inFile.get(c))
        {
            temp[counter] = c;
            counter++;
            if (counter == size)
            {
                for (int i = 0; i < size; i++)
                {
                    cipher[i] = temp[perm[i] - 1];
                }
                // writing the encrypted version to the output file and resetting the counter to repeat the process
                outFile << cipher;
                counter = 0;
            }
        }
        // checking if I need to do any padding
        if (counter < size)
        {
            while (counter < size)
            {
                temp[counter] = '*';
                counter++;
            }
            for (int i = 0; i < size; i++)
            {
                cipher[i] = temp[perm[i] - 1];
            }
        }
        outFile << cipher;
        outFile.close();
        inFile.close();
    }
    else
        cout << "Unable to open file";

    delete[] cipher;
    delete[] temp;
}

void Dec(ifstream &inFile, ofstream &outFile, int size, int *perm)
{
    char c;
    int counter = 0;
    char *temp = new char[size];
    char *cipher = new char[size];
    if (inFile.is_open())
    {
        while (inFile.get(c))
        {
            temp[counter] = c;
            counter++;
            if (counter == size)
            {
                for (int i = 0; i < size; i++)
                {
                    if (temp[perm[i] - 1] != '*')
                    {
                        cipher[i] = temp[perm[i] - 1];
                        outFile << cipher[i];
                    }
                }
                counter = 0;
            }
        }
        outFile.close();
        inFile.close();
    }
    else
        cout << "Unable to open file";

    delete[] cipher;
    delete[] temp;
}

int main(int argc, char **argv)
{
    int size = 0;
    int *perm;
    char opt;
    string inputFile;
    string outputFile;
    int eflag, dflag, iflag, oflag;
    eflag = 0;
    dflag = 0;
    iflag = 0;
    oflag = 0;

    while ((opt = getopt(argc, argv, "i:o:ed")) != -1)
    {
        switch (opt)
        {
        case 'i':
            iflag = 1;
            inputFile = optarg;
            break;
        case 'o':
            oflag = 1;
            outputFile = optarg;
            break;
        case 'e':
            eflag = 1;
            break;
        case 'd':
            dflag = 1;
            break;
        default:
            cout << "Unknown option " << argv[0] << endl;
        }
    }
    if (argc != 6 || (iflag == 0) || (oflag == 0) ||
        (eflag == dflag))
    {
        cout << "Error!" << endl;
    }
    ifstream inFile(inputFile, ios::in);
    ofstream outFile(outputFile, ios::app);
    cout << "please enter the block size (2-8): ";
    cin >> size;
    perm = new int[size];
    int temp = 0;
    cout << "\n please enter the permutation: ";
    cin >> temp;
    for (int i = (size - 1); i >= 0; i--)
    {
        perm[i] = temp % 10;
        temp /= 10;
    }
    if (eflag == 1)
    {
        Enc(inFile, outFile, size, perm);
    }
    else if (dflag == 1)
    {
        Dec(inFile, outFile, size, perm);
    }

    delete[] perm;

    return 0;
}
